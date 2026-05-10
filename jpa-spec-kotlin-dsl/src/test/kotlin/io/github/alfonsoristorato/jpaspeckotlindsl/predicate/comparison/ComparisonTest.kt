package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.AddressInfo
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
class ComparisonTest(
    private val personaRepository: PersonaRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                    age = 20,
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Persona 2",
                    age = 30,
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Persona 3",
                    age = 40,
                )
            val persona4 =
                TestFixtures.createPersona(
                    name = "Persona 4",
                    age = 30,
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3, persona4))
            personaRepository.findAll() shouldHaveSize 4

            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org A",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Alpha Street"),
                        ),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org B",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Beta Street"),
                        ),
                )
            val org3 =
                TestFixtures.createOrganisation(
                    name = "Org C",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Gamma Street"),
                        ),
                )
            organisationRepository.saveAll(listOf(org1, org2, org3))
            organisationRepository.findAll() shouldHaveSize 3
        }

        context("greaterThan for Predicate checks if property is greater than value") {
            expect("with comparable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.greaterThan(root, cb, 30)
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
            }
        }

        context("greaterThanOrEqualTo for Predicate checks if property is greater than or equal to value") {
            expect("with comparable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.greaterThanOrEqualTo(root, cb, 30)
                    }
                result shouldHaveSize 3
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[1].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
                result[2].apply {
                    name shouldBe "Persona 4"
                    age shouldBe 30
                }
            }
        }

        context("lessThan for Predicate checks if property is less than value") {
            expect("with comparable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.lessThan(root, cb, 30)
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }
        }

        context("lessThanOrEqualTo for Predicate checks if property is less than or equal to value") {
            expect("with comparable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.lessThanOrEqualTo(root, cb, 30)
                    }
                result shouldHaveSize 3
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
                result[1].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[2].apply {
                    name shouldBe "Persona 4"
                    age shouldBe 30
                }
            }
        }

        context("between for Predicate checks if property is between two values (inclusive)") {
            expect("with comparable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.between(root, cb, 25, 35)
                    }
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[1].apply {
                    name shouldBe "Persona 4"
                    age shouldBe 30
                }
            }
            expect("with inclusive boundaries") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.between(root, cb, 20, 40)
                    }
                result shouldHaveSize 4
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
                result[1].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[2].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
                result[3].apply {
                    name shouldBe "Persona 4"
                    age shouldBe 30
                }
            }
        }

        context("greaterThan for Predicate checks if nested property is greater than value") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .greaterThan(root, cb, "Beta Street")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org C"
            }
        }

        context("greaterThanOrEqualTo for Predicate checks if nested property is gte value") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .greaterThanOrEqualTo(root, cb, "Beta Street")
                    }
                result shouldHaveSize 2
                result[0].name shouldBe "Org B"
                result[1].name shouldBe "Org C"
            }
        }

        context("lessThan for Predicate checks if nested property is less than value") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .lessThan(root, cb, "Beta Street")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org A"
            }
        }

        context("lessThanOrEqualTo for Predicate checks if nested property is lte value") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .lessThanOrEqualTo(root, cb, "Beta Street")
                    }
                result shouldHaveSize 2
                result[0].name shouldBe "Org A"
                result[1].name shouldBe "Org B"
            }
        }

        context("between for Predicate checks if nested property is between two values") {
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .between(root, cb, "Alpha Street", "Beta Street")
                    }
                result shouldHaveSize 2
                result[0].name shouldBe "Org A"
                result[1].name shouldBe "Org B"
            }
        }
    })
