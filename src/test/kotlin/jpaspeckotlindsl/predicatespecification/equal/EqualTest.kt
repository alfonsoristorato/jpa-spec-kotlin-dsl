package jpaspeckotlindsl.predicatespecification.equal

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jpaspeckotlindsl.jpasetup.entity.Persona
import jpaspeckotlindsl.jpasetup.entity.Post
import jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import jpaspeckotlindsl.jpasetup.repository.PostRepository
import jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import jpaspeckotlindsl.util.TestFixtures

@SpringBootTestEnhanced
class EqualTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Persona 2",
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Persona 3",
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3

            val post1Persona1 =
                TestFixtures.createPost(
                    title = "Post 1",
                    persona = persona1,
                )
            val post2Persona1 =
                TestFixtures.createPost(
                    title = "Post 2",
                    persona = persona1,
                )
            val post1Persona2 =
                TestFixtures.createPost(
                    title = "Post 2",
                    persona = persona2,
                )
            val post1Persona3 =
                TestFixtures.createPost(
                    title = "Post 3",
                    persona = persona3,
                )
            postRepository.saveAll(listOf(post1Persona1, post2Persona1, post1Persona2, post1Persona3))
            postRepository.findAll() shouldHaveSize 4
        }

        context("equal for PredicateSpecification checks for equality") {
            expect("with declared types") {
                val persona1 = personaRepository.findAll().first { it.name == "Persona 1" }
                val postsWithSpecificPersona = Post::persona.equal(persona1)
                val result =
                    postRepository.findAll(postsWithSpecificPersona)
                result shouldHaveSize 2
                result[0].title shouldBe "Post 1"
                result[1].title shouldBe "Post 2"
            }
            expect("with primitive types") {
                val postsWithSpecificTitle = Post::title.equal("Post 2")
                val result1 =
                    postRepository.findAll(postsWithSpecificTitle)
                result1 shouldHaveSize 2
                result1[0].persona.name shouldBe "Persona 1"
                result1[1].persona.name shouldBe "Persona 2"

                val personasWithSpecificAge = Persona::age.equal(TestFixtures.DEFAULT_PERSONA_AGE)
                val result2 =
                    personaRepository.findAll(personasWithSpecificAge)
                result2 shouldHaveSize 3
                result2[0].name shouldBe "Persona 1"
                result2[1].name shouldBe "Persona 2"
                result2[2].name shouldBe "Persona 3"
            }
            expect("witl nullable types") {
                val personasWithSpecificLastName = Persona::lastName.equal(TestFixtures.DEFAULT_PERSONA_LAST_NAME)
                val result =
                    personaRepository.findAll(personasWithSpecificLastName)
                result shouldHaveSize 3
                result[0].name shouldBe "Persona 1"
                result[1].name shouldBe "Persona 2"
                result[2].name shouldBe "Persona 3"
            }
        }
    })
