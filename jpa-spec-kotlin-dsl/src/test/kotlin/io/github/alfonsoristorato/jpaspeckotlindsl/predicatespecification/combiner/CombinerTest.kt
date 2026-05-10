package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.combiner

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.comparison.greaterThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.comparison.lessThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
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
        context("and combines PredicateSpecification") {
            expect("combines one by one") {
                val idLowerThan3 = Persona::id.lessThan(3L)
                val ageGreaterThan20 = Persona::age.greaterThan(20)
                val lastNameEquals = Persona::lastName.equal("LastName")
                val result =
                    personaRepository.findAll(
                        idLowerThan3 and lastNameEquals and ageGreaterThan20,
                    )
                assertSoftly {
                    result shouldHaveSize 1
                    result[0].name shouldBe "Persona 2"
                }
            }

            expect("combines altogether") {
                val idLowerThan3 = Persona::id.lessThan(3L)
                val ageGreaterThan20 = Persona::age.greaterThan(20)
                val lastNameEquals = Persona::lastName.equal("LastName")
                val result =
                    personaRepository.findAll(
                        and(idLowerThan3, lastNameEquals, ageGreaterThan20),
                    )
                assertSoftly {
                    result shouldHaveSize 1
                    result[0].name shouldBe "Persona 2"
                }
            }
        }

        context("or combines PredicateSpecification") {
            expect("combines one by one") {
                val idLowerThan3 = Persona::id.lessThan(3L)
                val ageGreaterThan20 = Persona::age.greaterThan(20)
                val lastNameEquals = Persona::lastName.equal("LastName")
                val result =
                    personaRepository.findAll(
                        idLowerThan3 or lastNameEquals or ageGreaterThan20,
                    )
                assertSoftly {
                    result shouldHaveSize 3
                    result[0].name shouldBe "Persona 1"
                    result[1].name shouldBe "Persona 2"
                    result[2].name shouldBe "Persona 3"
                }
            }

            expect("combines altogether") {
                val idLowerThan3 = Persona::id.lessThan(3L)
                val ageGreaterThan20 = Persona::age.greaterThan(20)
                val lastNameEquals = Persona::lastName.equal("LastName")
                val result =
                    personaRepository.findAll(
                        or(idLowerThan3, lastNameEquals, ageGreaterThan20),
                    )
                assertSoftly {
                    result shouldHaveSize 3
                    result[0].name shouldBe "Persona 1"
                    result[1].name shouldBe "Persona 2"
                    result[2].name shouldBe "Persona 3"
                }
            }
        }
    })
