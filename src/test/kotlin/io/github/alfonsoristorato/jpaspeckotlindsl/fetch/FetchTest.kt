package io.github.alfonsoristorato.jpaspeckotlindsl.fetch

import io.github.alfonsoristorato.jpaspeckotlindsl.internal.ExperimentalJoinApi
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PostRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.datatest.withExpects
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jakarta.persistence.criteria.JoinType
import org.springframework.data.jpa.domain.Specification

@OptIn(ExperimentalJoinApi::class)
@SpringBootTestEnhanced
class FetchTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
) : ExpectSpec({
        beforeSpec {
            val persona =
                TestFixtures.createPersona(
                    name = "John Doe",
                    age = 25,
                )
            personaRepository.save(persona)
            personaRepository.findAll() shouldHaveSize 1

            val post =
                TestFixtures.createPost(
                    title = "Test Post",
                    persona = persona,
                )
            postRepository.save(post)
            postRepository.findAll() shouldHaveSize 1
        }

        context("fetch function") {
            expect("creates a fetch with default INNER JoinType") {
                val spec =
                    Specification<Post> { root, _, criteriaBuilder ->
                        Post::persona.fetch(root)
                        criteriaBuilder.conjunction()
                    }

                val result = postRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].persona.name shouldBe "John Doe"
            }

            withExpects(
                nameFn = { "creates a fetch with JoinType: $it" },
                JoinType.INNER,
                JoinType.LEFT,
                JoinType.RIGHT,
            ) { joinType ->
                val spec =
                    Specification<Post> { root, _, criteriaBuilder ->
                        Post::persona.fetch(root, joinType)
                        criteriaBuilder.conjunction()
                    }

                val result = postRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].persona.name shouldBe "John Doe"
            }
        }
    })
