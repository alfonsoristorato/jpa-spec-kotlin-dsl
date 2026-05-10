package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.string

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
class StringTest(
    private val personaRepository: PersonaRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "John Smith",
                    userName = "johnsmith123",
                    lastName = "Smith",
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Jane Doe",
                    userName = "janedoe456",
                    lastName = "Doe",
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Bob Johnson",
                    userName = "bobjohnson789",
                    lastName = null,
                )
            val persona4 =
                TestFixtures.createPersona(
                    name = "Alice Smith",
                    userName = "alicesmith000",
                    lastName = "Smith",
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3, persona4))
            personaRepository.findAll() shouldHaveSize 4

            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org Alpha",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Main Street"),
                            contactInfo = TestFixtures.contactInfo(nickname = "Ally"),
                        ),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org Beta",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Main Avenue"),
                        ),
                )
            val org3 =
                TestFixtures.createOrganisation(
                    name = "Org Gamma",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Oak Road"),
                            contactInfo = TestFixtures.contactInfo(nickname = "Gam"),
                        ),
                )
            organisationRepository.saveAll(listOf(org1, org2, org3))
            organisationRepository.findAll() shouldHaveSize 3
        }

        context("like for Predicate checks if property matches pattern") {
            expect("with prefix pattern") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::name.like(root, cb, "John%")
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "John Smith"
                    userName shouldBe "johnsmith123"
                }
            }
            expect("with suffix pattern") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::name.like(root, cb, "%Smith")
                    }
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "John Smith"
                }
                result[1].apply {
                    name shouldBe "Alice Smith"
                }
            }
            expect("with contains pattern") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::userName.like(root, cb, "%john%")
                    }
                result shouldHaveSize 2
                result[0].apply {
                    userName shouldBe "johnsmith123"
                }
                result[1].apply {
                    userName shouldBe "bobjohnson789"
                }
            }
            expect("with single character wildcard") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::name.like(root, cb, "J__e Doe")
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Jane Doe"
                }
            }
            expect("with nullable property") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::lastName.like(root, cb, "Smith")
                    }
                result shouldHaveSize 2
                result[0].apply {
                    lastName shouldBe "Smith"
                }
                result[1].apply {
                    lastName shouldBe "Smith"
                }
            }
        }

        context("notLike for Predicate checks if property does not match pattern") {
            expect("with prefix pattern") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::name.notLike(root, cb, "John%")
                    }
                result shouldHaveSize 3
                result[0].apply {
                    name shouldBe "Jane Doe"
                }
                result[1].apply {
                    name shouldBe "Bob Johnson"
                }
                result[2].apply {
                    name shouldBe "Alice Smith"
                }
            }
            expect("with contains pattern") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::userName.notLike(root, cb, "%smith%")
                    }
                result shouldHaveSize 2
                result[0].apply {
                    userName shouldBe "janedoe456"
                }
                result[1].apply {
                    userName shouldBe "bobjohnson789"
                }
            }
            expect("with nullable property") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::lastName.notLike(root, cb, "Smith")
                    }
                result shouldHaveSize 1
                result[0].apply {
                    lastName shouldBe "Doe"
                }
            }
        }

        context("like for Predicate checks if nested property matches pattern") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .like(root, cb, "Main%")
                    }
                result shouldHaveSize 2
                result[0].name shouldBe "Org Alpha"
                result[1].name shouldBe "Org Beta"
            }
            expect("with nested nullable leaf property") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::contactInfo / ContactInfo::nickname)
                            .like(root, cb, "A%")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org Alpha"
            }
        }

        context("notLike for Predicate checks if nested property does not match pattern") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .notLike(root, cb, "Main%")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org Gamma"
            }
            expect("with nested nullable leaf property") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::contactInfo / ContactInfo::nickname)
                            .notLike(root, cb, "A%")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org Gamma"
            }
        }
    })
