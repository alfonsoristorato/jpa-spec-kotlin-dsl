package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.AddressInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Organisation
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.OrganisationInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.OrganisationRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PostRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class EqualityTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
    private val organisationRepository: OrganisationRepository,
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
                    title = "Post 1 - Persona 1",
                    persona = persona1,
                )
            val post2Persona1 =
                TestFixtures.createPost(
                    title = "Post 2 - Persona 1",
                    persona = persona1,
                )
            val post1Persona2 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 2",
                    persona = persona2,
                )
            val post1Persona3 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 3",
                    persona = persona3,
                )
            postRepository.saveAll(listOf(post1Persona1, post2Persona1, post1Persona2, post1Persona3))
            postRepository.findAll() shouldHaveSize 4

            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org 1",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Street 1"),
                        ),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org 2",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Street 2"),
                        ),
                )
            organisationRepository.saveAll(listOf(org1, org2))
            organisationRepository.findAll() shouldHaveSize 2
        }

        context("equal for Predicate checks for equality") {
            expect("with declared types") {
                val persona1 = personaRepository.findAll().first { it.name == "Persona 1" }
                val result =
                    postRepository.findAll { root, _, cb ->
                        Post::persona.equal(root, cb, persona1)
                    }
                result shouldHaveSize 2
                result[0].title shouldBe "Post 1 - Persona 1"
                result[1].title shouldBe "Post 2 - Persona 1"
            }
            expect("with primitive types") {
                val result1 =
                    postRepository.findAll { root, _, cb ->
                        Post::title.equal(root, cb, "Post 1 - Persona 2")
                    }
                result1 shouldHaveSize 1
                result1[0].apply {
                    title shouldBe "Post 1 - Persona 2"
                    persona.name shouldBe "Persona 2"
                }
                val result2 =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.equal(root, cb, TestFixtures.DEFAULT_PERSONA_AGE)
                    }
                result2 shouldHaveSize 3
                result2[0].name shouldBe "Persona 1"
                result2[1].name shouldBe "Persona 2"
                result2[2].name shouldBe "Persona 3"
            }
            expect("with nullable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::lastName.equal(root, cb, TestFixtures.DEFAULT_PERSONA_LAST_NAME)
                    }
                result shouldHaveSize 3
                result[0].name shouldBe "Persona 1"
                result[1].name shouldBe "Persona 2"
                result[2].name shouldBe "Persona 3"
            }
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .equal(root, cb, "Street 1")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org 1"
            }
        }

        context("notEqual for Predicate checks for inequality") {
            expect("with declared types") {
                val persona1 = personaRepository.findAll().first { it.name == "Persona 1" }
                val result =
                    postRepository.findAll { root, _, cb ->
                        Post::persona.notEqual(root, cb, persona1)
                    }
                result shouldHaveSize 2
                result[0].title shouldBe "Post 1 - Persona 2"
                result[1].title shouldBe "Post 1 - Persona 3"
            }
            expect("with primitive types") {
                val result1 =
                    postRepository.findAll { root, _, cb ->
                        Post::title.notEqual(root, cb, "Post 1 - Persona 1")
                    }
                result1 shouldHaveSize 3
                result1[0].apply {
                    title shouldBe "Post 2 - Persona 1"
                    persona.name shouldBe "Persona 1"
                }
                result1[1].apply {
                    title shouldBe "Post 1 - Persona 2"
                    persona.name shouldBe "Persona 2"
                }
                result1[2].apply {
                    title shouldBe "Post 1 - Persona 3"
                    persona.name shouldBe "Persona 3"
                }
                val result2 =
                    personaRepository.findAll { root, _, cb ->
                        Persona::age.notEqual(root, cb, TestFixtures.DEFAULT_PERSONA_AGE)
                    }
                result2 shouldHaveSize 0
            }
            expect("with nullable types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        Persona::lastName.notEqual(root, cb, TestFixtures.DEFAULT_PERSONA_LAST_NAME)
                    }
                result shouldHaveSize 0
            }
            expect("with nested types") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                            .notEqual(root, cb, "Street 1")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org 2"
            }
        }
    })
