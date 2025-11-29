package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.comparison

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class ComparisonTest(
    private val personaRepository: PersonaRepository,
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
        }

        context("greaterThan for PredicateSpecification checks if property is greater than value") {
            expect("with comparable types") {
                val personasOlderThan30 = Persona::age.greaterThan(30)
                val result = personaRepository.findAll(personasOlderThan30)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
            }
        }

        context("greaterThanOrEqualTo for PredicateSpecification checks if property is greater than or equal to value") {
            expect("with comparable types") {
                val personas30OrOlder = Persona::age.greaterThanOrEqualTo(30)
                val result = personaRepository.findAll(personas30OrOlder)
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

        context("lessThan for PredicateSpecification checks if property is less than value") {
            expect("with comparable types") {
                val personasYoungerThan30 = Persona::age.lessThan(30)
                val result = personaRepository.findAll(personasYoungerThan30)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }
        }

        context("lessThanOrEqualTo for PredicateSpecification checks if property is less than or equal to value") {
            expect("with comparable types") {
                val personas30OrYounger = Persona::age.lessThanOrEqualTo(30)
                val result = personaRepository.findAll(personas30OrYounger)
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

        context("between for PredicateSpecification checks if property is between two values (inclusive)") {
            expect("with comparable types") {
                val personasBetween25And35 = Persona::age.between(25, 35)
                val result = personaRepository.findAll(personasBetween25And35)
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
                val personasBetween20And40 = Persona::age.between(20, 40)
                val result = personaRepository.findAll(personasBetween20And40)
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
    })
