package jpaspeckotlindsl.specification.string

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jpaspeckotlindsl.jpasetup.entity.Persona
import jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import jpaspeckotlindsl.util.TestFixtures

@SpringBootTestEnhanced
class StringTest(
    private val personaRepository: PersonaRepository,
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
        }

        context("like for Specification checks if property matches pattern") {
            expect("with prefix pattern") {
                val personasStartingWithJohn = Persona::name.like("John%")
                val result = personaRepository.findAll(personasStartingWithJohn)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "John Smith"
                    userName shouldBe "johnsmith123"
                }
            }
            expect("with suffix pattern") {
                val personasEndingWithSmith = Persona::name.like("%Smith")
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
                val personasContainingJohn = Persona::userName.like("%john%")
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
                val personasWithDoePattern = Persona::name.like("J__e Doe")
                val result = personaRepository.findAll(personasWithDoePattern)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Jane Doe"
                }
            }
            expect("with nullable property") {
                val personasWithLastNameSmith = Persona::lastName.like("Smith")
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

        context("notLike for Specification checks if property does not match pattern") {
            expect("with prefix pattern") {
                val personasNotStartingWithJohn = Persona::name.notLike("John%")
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
                val personasNotContainingSmith = Persona::userName.notLike("%smith%")
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
                val personasWithLastNameNotSmith = Persona::lastName.notLike("Smith")
                val result = personaRepository.findAll(personasWithLastNameNotSmith)
                result shouldHaveSize 1
                result[0].apply {
                    lastName shouldBe "Doe"
                }
            }
        }
    })
