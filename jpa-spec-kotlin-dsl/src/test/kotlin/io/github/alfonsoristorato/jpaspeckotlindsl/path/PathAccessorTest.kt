package io.github.alfonsoristorato.jpaspeckotlindsl.path

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.AddressInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Organisation
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.OrganisationInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository.OrganisationRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class PathAccessorTest(
    private val personaRepository: PersonaRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 = TestFixtures.createPersona(name = "Persona 1")
            val persona2 = TestFixtures.createPersona(name = "Persona 2")
            personaRepository.saveAll(listOf(persona1, persona2))
            personaRepository.findAll() shouldHaveSize 2

            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org 1",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Street 1"),
                        ),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org 2",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Street 2"),
                        ),
                )
            organisationRepository.saveAll(listOf(org1, org2))
            organisationRepository.findAll() shouldHaveSize 2
        }

        context("path for KProperty1 resolves to the property's JPA Path") {
            expect("usable directly with CriteriaBuilder") {
                val result =
                    personaRepository.findAll { root, _, criteriaBuilder ->
                        criteriaBuilder.equal(Persona::name.path(root), "Persona 2")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 2"
            }
        }

        context("path for NestedProperty resolves to the nested property's JPA Path") {
            expect("usable directly with CriteriaBuilder") {
                val result =
                    organisationRepository.findAll { root, _, criteriaBuilder ->
                        criteriaBuilder.equal(
                            (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street).path(root),
                            "Street 1",
                        )
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org 1"
            }

            @Suppress("DEPRECATION")
            expect("deprecated resolve() still delegates to path()") {
                val result =
                    organisationRepository.findAll { root, _, criteriaBuilder ->
                        criteriaBuilder.equal(
                            (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street).resolve(root),
                            "Street 1",
                        )
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org 1"
            }
        }
    })
