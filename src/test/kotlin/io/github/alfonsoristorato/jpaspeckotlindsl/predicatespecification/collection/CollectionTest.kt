package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PostRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class CollectionTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                )
            personaRepository.save(persona1)
            personaRepository.findAll() shouldHaveSize 1

            val post1 =
                TestFixtures.createPost(
                    title = "Post 1",
                    persona = persona1,
                    tags = setOf("kotlin", "jpa"),
                )
            val post2 =
                TestFixtures.createPost(
                    title = "Post 2",
                    persona = persona1,
                    tags = setOf("java", "spring"),
                )
            val post3 =
                TestFixtures.createPost(
                    title = "Post 3",
                    persona = persona1,
                    tags = emptySet(),
                )
            postRepository.saveAll(listOf(post1, post2, post3))
            postRepository.findAll() shouldHaveSize 3
        }

        context("isEmpty for PredicateSpecification tests whether a collection is empty") {
            expect("returns posts with empty tags") {
                val spec = Post::tags.isEmpty()
                val result = postRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].title shouldBe "Post 3"
            }
        }

        context("isNotEmpty for PredicateSpecification tests whether a collection is not empty") {
            expect("returns posts with non-empty tags") {
                val spec = Post::tags.isNotEmpty()
                val result = postRepository.findAll(spec)
                result shouldHaveSize 2
                result.map { it.title } shouldBe listOf("Post 1", "Post 2")
            }
        }

        context("isMember for PredicateSpecification tests whether an element is a member of a collection") {
            expect("returns posts where the tag is a member") {
                val spec = Post::tags.isMember("kotlin")
                val result = postRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].title shouldBe "Post 1"
            }
            expect("returns empty when tag is not a member of any collection") {
                val spec = Post::tags.isMember("nonexistent")
                val result = postRepository.findAll(spec)
                result shouldHaveSize 0
            }
        }

        context("isNotMember for PredicateSpecification tests whether an element is not a member of a collection") {
            expect("returns posts where the tag is not a member") {
                val spec = Post::tags.isNotMember("kotlin")
                val result = postRepository.findAll(spec)
                result shouldHaveSize 2
                result.map { it.title } shouldBe listOf("Post 2", "Post 3")
            }
            expect("returns all posts when tag doesn't exist in any collection") {
                val spec = Post::tags.isNotMember("nonexistent")
                val result = postRepository.findAll(spec)
                result shouldHaveSize 3
            }
        }
    })
