package io.github.alfonsoristorato.jpaspeckotlindsl.specification.bool

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.AddressInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.ContactInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Organisation
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.OrganisationInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.OrganisationRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class BoolTest(
    private val personaRepository: PersonaRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                    firstLogin = true,
                    isMagic = false,
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Persona 2",
                    firstLogin = false,
                    isMagic = null,
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Persona 3",
                    firstLogin = false,
                    isMagic = true,
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3

            val org1 =
                TestFixtures.createOrganisation(
                    name = "Active Org",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(isActive = true),
                            contactInfo = TestFixtures.contactInfo(isVerified = true),
                        ),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Inactive Org",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(isActive = false),
                            contactInfo = TestFixtures.contactInfo(isVerified = false),
                        ),
                )
            organisationRepository.saveAll(listOf(org1, org2))
            organisationRepository.findAll() shouldHaveSize 2
        }
        context("isTrue for Specification checks for Boolean true") {
            expect("with non-nullable Boolean property") {
                val result =
                    personaRepository.findAll(
                        Persona::firstLogin.isTrue(),
                    )
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 1"
            }
            expect("with nullable Boolean property") {
                val result =
                    personaRepository.findAll(
                        Persona::isMagic.isTrue(),
                    )
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 3"
            }
        }
        context("isFalse for Specification checks for Boolean false") {
            expect("with non-nullable Boolean property") {
                val result =
                    personaRepository.findAll(
                        Persona::firstLogin.isFalse(),
                    )
                result shouldHaveSize 2
                result.map { it.name }.toSet() shouldBe setOf("Persona 2", "Persona 3")
            }
            expect("with nullable Boolean property") {
                val result =
                    personaRepository.findAll(
                        Persona::isMagic.isFalse(),
                    )
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 1"
            }
        }
        context("isTrue for Specification checks for nested Boolean true") {
            expect("with nested types") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::isActive)
                        .isTrue()
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Active Org"
            }
            expect("with nested nullable Boolean property") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::contactInfo / ContactInfo::isVerified)
                        .isTrue()
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Active Org"
            }
        }
        context("isFalse for Specification checks for nested Boolean false") {
            expect("with nested types") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::isActive)
                        .isFalse()
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Inactive Org"
            }
            expect("with nested nullable Boolean property") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::contactInfo / ContactInfo::isVerified)
                        .isFalse()
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Inactive Org"
            }
        }
    })
