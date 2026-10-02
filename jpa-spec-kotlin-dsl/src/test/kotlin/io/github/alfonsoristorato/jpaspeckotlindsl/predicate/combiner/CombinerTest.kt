package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.combiner

import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.greaterThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.lessThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.util.TestFixtures
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class CombinerTest(
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
                    age = 25,
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Persona 3",
                    age = 30,
                )
            val persona4 =
                TestFixtures.createPersona(
                    name = "Persona 4",
                    age = 18,
                    lastName = "DifferentLastName",
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3, persona4))
            personaRepository.findAll() shouldHaveSize 4
        }
        context("and combines Predicates") {
            expect("combines two") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        val idLowerThan3 = Persona::id.lessThan(root, cb, 3L)
                        val lastNameEquals = Persona::lastName.equal(root, cb, "LastName")
                        and(cb, idLowerThan3, lastNameEquals)
                    }
                assertSoftly {
                    result shouldHaveSize 2
                    result[0].name shouldBe "Persona 1"
                    result[1].name shouldBe "Persona 2"
                }
            }

            expect("combines more than two") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        val idLowerThan3 = Persona::id.lessThan(root, cb, 3L)
                        val ageGreaterThan20 = Persona::age.greaterThan(root, cb, 20)
                        val lastNameEquals = Persona::lastName.equal(root, cb, "LastName")
                        and(cb, idLowerThan3, lastNameEquals, ageGreaterThan20)
                    }
                assertSoftly {
                    result shouldHaveSize 1
                    result[0].name shouldBe "Persona 2"
                }
            }
        }

        context("or combines Predicates") {
            expect("combines two") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        val ageGreaterThan20 = Persona::age.greaterThan(root, cb, 20)
                        val lastNameEquals = Persona::lastName.equal(root, cb, "DifferentLastName")
                        or(cb, ageGreaterThan20, lastNameEquals)
                    }
                assertSoftly {
                    result shouldHaveSize 3
                    result[0].name shouldBe "Persona 2"
                    result[1].name shouldBe "Persona 3"
                    result[2].name shouldBe "Persona 4"
                }
            }

            expect("combines more than two") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        val idLowerThan3 = Persona::id.lessThan(root, cb, 3L)
                        val ageGreaterThan20 = Persona::age.greaterThan(root, cb, 20)
                        val lastNameEquals = Persona::lastName.equal(root, cb, "DifferentLastName")
                        or(cb, idLowerThan3, ageGreaterThan20, lastNameEquals)
                    }
                assertSoftly {
                    result shouldHaveSize 4
                    result[0].name shouldBe "Persona 1"
                    result[1].name shouldBe "Persona 2"
                    result[2].name shouldBe "Persona 3"
                    result[3].name shouldBe "Persona 4"
                }
            }
        }
    })
