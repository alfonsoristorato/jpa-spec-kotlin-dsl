package jpaspeckotlindsl.specification.bool

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jpaspeckotlindsl.jpasetup.entity.Persona
import jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import jpaspeckotlindsl.util.TestFixtures

@SpringBootTestEnhanced
class BoolTest(
    private val personaRepository: PersonaRepository,
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
    })
