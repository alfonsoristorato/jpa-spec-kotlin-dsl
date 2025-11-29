package io.github.alfonsoristorato.jpaspeckotlindsl.join

import io.github.alfonsoristorato.jpaspeckotlindsl.internal.ExperimentalJoinApi
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Comment
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.CommentRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PostRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jakarta.persistence.criteria.JoinType
import org.springframework.data.jpa.domain.PredicateSpecification
import org.springframework.data.jpa.domain.Specification

@OptIn(ExperimentalJoinApi::class)
@SpringBootTestEnhanced
class JoinTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "John Doe",
                    age = 25,
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Jane Smith",
                    age = 30,
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Bob Johnson",
                    age = 35,
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3

            val post1 =
                TestFixtures.createPost(
                    title = "John's First Post",
                    persona = persona1,
                )
            val post2 =
                TestFixtures.createPost(
                    title = "John's Second Post",
                    persona = persona1,
                )
            val post3 =
                TestFixtures.createPost(
                    title = "Jane's Post",
                    persona = persona2,
                )
            postRepository.saveAll(listOf(post1, post2, post3))
            postRepository.findAll() shouldHaveSize 3

            val comment1 =
                TestFixtures.createComment(
                    post = post1,
                    persona = persona1,
                    content = "John's comment on his first post",
                )
            val comment2 =
                TestFixtures.createComment(
                    post = post1,
                    persona = persona2,
                    content = "Jane's comment on John's first post",
                )
            val comment3 =
                TestFixtures.createComment(
                    post = post2,
                    persona = persona1,
                    content = "John's comment on his second post",
                )
            val comment4 =
                TestFixtures.createComment(
                    post = post3,
                    persona = persona2,
                    content = "Jane's comment on her post",
                )
            val comment5 =
                TestFixtures.createComment(
                    post = post3,
                    persona = persona3,
                    content = "Bob's comment on Jane's post",
                )
            commentRepository.saveAll(listOf(comment1, comment2, comment3, comment4, comment5))
            commentRepository.findAll() shouldHaveSize 5
        }

        context("join creates joins for use with Specification") {
            context("inner join") {
                expect("Comment to Post") {
                    val spec =
                        Specification { root, _, criteriaBuilder ->
                            val postJoin = Comment::post.join(root)
                            Post::title.equal(postJoin, criteriaBuilder, "John's First Post")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "John's comment on his first post"
                        post.title shouldBe "John's First Post"
                    }
                    result[1].apply {
                        content shouldBe "Jane's comment on John's first post"
                        post.title shouldBe "John's First Post"
                    }
                }

                expect("Comment to Persona") {
                    val spec =
                        Specification { root, _, criteriaBuilder ->
                            val personaJoin = Comment::persona.join(root)
                            Persona::name.equal(personaJoin, criteriaBuilder, "Jane Smith")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "Jane's comment on John's first post"
                        persona.name shouldBe "Jane Smith"
                    }
                    result[1].apply {
                        content shouldBe "Jane's comment on her post"
                        persona.name shouldBe "Jane Smith"
                    }
                }
            }
            context("left join") {
                expect("Comment to Post") {
                    val spec =
                        Specification { root, _, criteriaBuilder ->
                            val postJoin = Comment::post.join(root, JoinType.LEFT)
                            Post::title.equal(postJoin, criteriaBuilder, "Jane's Post")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "Jane's comment on her post"
                        post.title shouldBe "Jane's Post"
                    }
                    result[1].apply {
                        content shouldBe "Bob's comment on Jane's post"
                        post.title shouldBe "Jane's Post"
                    }
                }

                expect("Comment to Persona") {
                    val spec =
                        Specification { root, _, criteriaBuilder ->
                            val personaJoin = Comment::persona.join(root, JoinType.LEFT)
                            Persona::age.equal(personaJoin, criteriaBuilder, 35)
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 1
                    result[0].apply {
                        content shouldBe "Bob's comment on Jane's post"
                        persona.name shouldBe "Bob Johnson"
                        persona.age shouldBe 35
                    }
                }
            }
            context("right join") {
                expect("Comment to Post") {
                    val spec =
                        Specification { root, _, criteriaBuilder ->
                            val postJoin = Comment::post.join(root, JoinType.RIGHT)
                            Post::title.equal(postJoin, criteriaBuilder, "John's Second Post")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 1
                    result[0].apply {
                        content shouldBe "John's comment on his second post"
                        post.title shouldBe "John's Second Post"
                    }
                }

                expect("Comment to Persona") {
                    val spec =
                        Specification { root, _, criteriaBuilder ->
                            val personaJoin = Comment::persona.join(root, JoinType.RIGHT)
                            Persona::name.equal(personaJoin, criteriaBuilder, "John Doe")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "John's comment on his first post"
                        persona.name shouldBe "John Doe"
                    }
                    result[1].apply {
                        content shouldBe "John's comment on his second post"
                        persona.name shouldBe "John Doe"
                    }
                }
            }
        }
        context("join creates joins for use with PredicateSpecification") {
            context("inner join") {
                expect("Comment to Post") {
                    val spec =
                        PredicateSpecification { from, criteriaBuilder ->
                            val postJoin = Comment::post.join(from)
                            Post::title.equal(postJoin, criteriaBuilder, "John's First Post")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "John's comment on his first post"
                        post.title shouldBe "John's First Post"
                    }
                    result[1].apply {
                        content shouldBe "Jane's comment on John's first post"
                        post.title shouldBe "John's First Post"
                    }
                }

                expect("Comment to Persona") {
                    val spec =
                        PredicateSpecification { from, criteriaBuilder ->
                            val personaJoin = Comment::persona.join(from)
                            Persona::name.equal(personaJoin, criteriaBuilder, "Jane Smith")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "Jane's comment on John's first post"
                        persona.name shouldBe "Jane Smith"
                    }
                    result[1].apply {
                        content shouldBe "Jane's comment on her post"
                        persona.name shouldBe "Jane Smith"
                    }
                }
            }
            context("left join") {
                expect("Comment to Post") {
                    val spec =
                        PredicateSpecification { from, criteriaBuilder ->
                            val postJoin = Comment::post.join(from, JoinType.LEFT)
                            Post::title.equal(postJoin, criteriaBuilder, "Jane's Post")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "Jane's comment on her post"
                        post.title shouldBe "Jane's Post"
                    }
                    result[1].apply {
                        content shouldBe "Bob's comment on Jane's post"
                        post.title shouldBe "Jane's Post"
                    }
                }

                expect("Comment to Persona") {
                    val spec =
                        PredicateSpecification { from, criteriaBuilder ->
                            val personaJoin = Comment::persona.join(from, JoinType.LEFT)
                            Persona::age.equal(personaJoin, criteriaBuilder, 35)
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 1
                    result[0].apply {
                        content shouldBe "Bob's comment on Jane's post"
                        persona.name shouldBe "Bob Johnson"
                        persona.age shouldBe 35
                    }
                }
            }
            context("right join") {
                expect("Comment to Post") {
                    val spec =
                        PredicateSpecification { from, criteriaBuilder ->
                            val postJoin = Comment::post.join(from, JoinType.RIGHT)
                            Post::title.equal(postJoin, criteriaBuilder, "John's Second Post")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 1
                    result[0].apply {
                        content shouldBe "John's comment on his second post"
                        post.title shouldBe "John's Second Post"
                    }
                }

                expect("Comment to Persona") {
                    val spec =
                        PredicateSpecification { from, criteriaBuilder ->
                            val personaJoin = Comment::persona.join(from, JoinType.RIGHT)
                            Persona::name.equal(personaJoin, criteriaBuilder, "John Doe")
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 2
                    result[0].apply {
                        content shouldBe "John's comment on his first post"
                        persona.name shouldBe "John Doe"
                    }
                    result[1].apply {
                        content shouldBe "John's comment on his second post"
                        persona.name shouldBe "John Doe"
                    }
                }
            }
        }
    })
