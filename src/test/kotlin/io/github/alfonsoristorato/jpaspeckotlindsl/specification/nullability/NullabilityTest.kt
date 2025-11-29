package io.github.alfonsoristorato.jpaspeckotlindsl.specification.nullability

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class NullabilityTest(
    private val personaRepository: PersonaRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                    lastName = "Last Name 1",
                    isMagic = false,
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Persona 2",
                    lastName = "Last Name 2",
                    isMagic = null,
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Persona 3",
                    lastName = null,
                    isMagic = true,
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3
        }
        context("isNull for Specification checks for property to be null") {
            expect("with non-nullable property") {
                val result =
                    personaRepository.findAll(
                        Persona::firstLogin.isNull(),
                    )
                result shouldHaveSize 0
            }
            expect("with nullable property - isMagic") {
                val result =
                    personaRepository.findAll(
                        Persona::isMagic.isNull(),
                    )
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 2"
            }
            expect("with nullable property - lastName") {
                val result =
                    personaRepository.findAll(
                        Persona::lastName.isNull(),
                    )
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 3"
            }
        }
        context("isNotNull for Specification checks for property to not be null") {
            expect("with non-nullable property") {
                val result =
                    personaRepository.findAll(
                        Persona::firstLogin.isNotNull(),
                    )
                result shouldHaveSize 3
                result.map { it.name }.toSet() shouldBe setOf("Persona 1", "Persona 2", "Persona 3")
            }
            expect("with nullable property - isMagic") {
                val result =
                    personaRepository.findAll(
                        Persona::isMagic.isNotNull(),
                    )
                result shouldHaveSize 2
                result.map { it.name }.toSet() shouldBe setOf("Persona 1", "Persona 3")
            }
            expect("with nullable property - lastName") {
                val result =
                    personaRepository.findAll(
                        Persona::lastName.isNotNull(),
                    )
                result shouldHaveSize 2
                result.map { it.name }.toSet() shouldBe setOf("Persona 1", "Persona 2")
            }
        }
    })
