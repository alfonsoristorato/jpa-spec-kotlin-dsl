package io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.AddressInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.ContactInfo
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

        context("ilike for Specification checks if property matches pattern ignoring case") {
            expect("with prefix pattern") {
                val personasStartingWithJohn = Persona::name.ilike("john%")
                val result = personaRepository.findAll(personasStartingWithJohn)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "John Smith"
                    userName shouldBe "johnsmith123"
                }
            }
            expect("with suffix pattern") {
                val personasEndingWithSmith = Persona::name.ilike("%smith")
                val result = personaRepository.findAll(personasEndingWithSmith)
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "John Smith"
                }
                result[1].apply {
                    name shouldBe "Alice Smith"
                }
            }
            expect("with contains pattern") {
                val personasContainingJohn = Persona::userName.ilike("%JOHN%")
                val result = personaRepository.findAll(personasContainingJohn)
                result shouldHaveSize 2
                result[0].apply {
                    userName shouldBe "johnsmith123"
                }
                result[1].apply {
                    userName shouldBe "bobjohnson789"
                }
            }
            expect("with single character wildcard") {
                val personasWithDoePattern = Persona::name.ilike("J__E DOE")
                val result = personaRepository.findAll(personasWithDoePattern)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Jane Doe"
                }
            }
            expect("with nullable property") {
                val personasWithLastNameSmith = Persona::lastName.ilike("SMITH")
                val result = personaRepository.findAll(personasWithLastNameSmith)
                result shouldHaveSize 2
                result[0].apply {
                    lastName shouldBe "Smith"
                }
                result[1].apply {
                    lastName shouldBe "Smith"
                }
            }
        }

        context("notIlike for Specification checks if property does not match pattern ignoring case") {
            expect("with prefix pattern") {
                val personasNotStartingWithJohn = Persona::name.notIlike("JOHN%")
                val result = personaRepository.findAll(personasNotStartingWithJohn)
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
                val personasNotContainingSmith = Persona::userName.notIlike("%SMITH%")
                val result = personaRepository.findAll(personasNotContainingSmith)
                result shouldHaveSize 2
                result[0].apply {
                    userName shouldBe "janedoe456"
                }
                result[1].apply {
                    userName shouldBe "bobjohnson789"
                }
            }
            expect("with nullable property") {
                val personasWithLastNameNotSmith = Persona::lastName.notIlike("SMITH")
                val result = personaRepository.findAll(personasWithLastNameNotSmith)
                result shouldHaveSize 1
                result[0].apply {
                    lastName shouldBe "Doe"
                }
            }
        }

        context("ilike for Specification checks if nested property matches pattern ignoring case") {
            expect("with nested types") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                        .ilike("MAIN%")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].name shouldBe "Org Alpha"
                result[1].name shouldBe "Org Beta"
            }
            expect("with nested nullable leaf property") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::contactInfo / ContactInfo::nickname)
                        .ilike("a%")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Org Alpha"
            }
        }

        context("notIlike for Specification checks if nested property does not match pattern ignoring case") {
            expect("with nested types") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                        .notIlike("MAIN%")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Org Gamma"
            }
            expect("with nested nullable leaf property") {
                val spec =
                    (Organisation::organisationInfo / OrganisationInfo::contactInfo / ContactInfo::nickname)
                        .notIlike("a%")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Org Gamma"
            }
        }
    })
