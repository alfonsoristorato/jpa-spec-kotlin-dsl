package io.github.alfonsoristorato.jpaspeckotlindsl.specification.inclusion

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PostRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class InclusionTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
) : ExpectSpec({
        val persona1 =
            TestFixtures.createPersona(
                name = "Persona 1",
                age = 20,
                firstLogin = true,
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
        beforeSpec {
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3

            val post1 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 1",
                    persona = persona1,
                )
            val post2 =
                TestFixtures.createPost(
                    title = "Post 2 - Persona 1",
                    persona = persona1,
                )
            val post3 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 2",
                    persona = persona2,
                )
            val post4 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 3",
                    persona = persona3,
                )
            postRepository.saveAll(listOf(post1, post2, post3, post4))
            postRepository.findAll() shouldHaveSize 4
        }

        context("in for PredicateSpecification checks if property is in the given value") {
            expect("with single int") {
                val spec = Persona::age.`in`(30)
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
            }

            expect("with collection of int") {
                val spec = Persona::age.`in`(listOf(30, 40))
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[1].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
            }

            expect("with single bool") {
                val spec = Persona::firstLogin.`in`(true)
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }

            expect("with collection of bool") {
                val spec = Persona::firstLogin.`in`(listOf(true, false))
                val result = personaRepository.findAll(spec)
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
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
            }

            expect("with single string") {
                val spec = Persona::name.`in`("Persona 1")
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }

            expect("with collection of string") {
                val spec = Persona::name.`in`(listOf("Persona 1", "Persona 2"))
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
                result[1].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
            }

            expect("with single declared type") {
                val spec = Post::persona.`in`(persona1)
                val result = postRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].apply {
                    title shouldBe "Post 1 - Persona 1"
                }
                result[1].apply {
                    title shouldBe "Post 2 - Persona 1"
                }
            }

            expect("with collection of declared type") {
                val spec = Post::persona.`in`(listOf(persona1, persona2))
                val result = postRepository.findAll(spec)
                result shouldHaveSize 3
                result[0].apply {
                    title shouldBe "Post 1 - Persona 1"
                }
                result[1].apply {
                    title shouldBe "Post 2 - Persona 1"
                }
                result[2].apply {
                    title shouldBe "Post 1 - Persona 2"
                }
            }
        }
    })
