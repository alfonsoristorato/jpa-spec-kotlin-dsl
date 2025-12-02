package io.github.alfonsoristorato.jpaspeckotlindsl.specification.join

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
                    content = "Post 1 content",
                )
            val post2 =
                TestFixtures.createPost(
                    title = "John's Second Post",
                    persona = persona1,
                    content = "Post 2 content",
                )
            val post3 =
                TestFixtures.createPost(
                    title = "Jane's Post",
                    persona = persona2,
                    content = "Post 3 content",
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

        context("joinWithPredicate creates joins and applies the provided predicate") {
            context("inner join") {
                expect("Comment to Post") {
                    val spec =
                        Comment::post.joinWithPredicate { postJoin, criteriaBuilder ->
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
                        Comment::persona.joinWithPredicate { personaJoin, criteriaBuilder ->
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
                        Comment::post.joinWithPredicate(joinType = JoinType.LEFT) { postJoin, criteriaBuilder ->
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
                        Comment::persona.joinWithPredicate(joinType = JoinType.LEFT) { personaJoin, criteriaBuilder ->
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
                        Comment::post.joinWithPredicate(joinType = JoinType.RIGHT) { postJoin, criteriaBuilder ->
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
                        Comment::persona.joinWithPredicate(joinType = JoinType.RIGHT) { personaJoin, criteriaBuilder ->
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

        /**
         * TODO
         * Below provies that combining multiple conditions works as expected as no results are found
         * Should we in future have a more suitable data set to test positive matches for multiple conditions we can change these tests
         */
        context("joinWithPredicates creates joins and applies the provided predicates") {
            context("inner join") {
                expect("Comment to Post") {
                    val spec =
                        Comment::post.joinWithPredicates { postJoin, criteriaBuilder ->
                            listOf(
                                Post::title.equal(postJoin, criteriaBuilder, "John's First Post"),
                                Post::content.equal(postJoin, criteriaBuilder, "Post 2 content"),
                            )
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 0
                }

                expect("Comment to Persona") {
                    val spec =
                        Comment::persona.joinWithPredicates { personaJoin, criteriaBuilder ->
                            listOf(
                                Persona::name.equal(personaJoin, criteriaBuilder, "Jane Smith"),
                                Persona::age.equal(personaJoin, criteriaBuilder, 99),
                            )
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 0
                }
            }
            context("left join") {
                expect("Comment to Post") {
                    val spec =
                        Comment::post.joinWithPredicates(joinType = JoinType.LEFT) { postJoin, criteriaBuilder ->
                            listOf(
                                Post::title.equal(postJoin, criteriaBuilder, "Jane's Post"),
                                Post::content.equal(postJoin, criteriaBuilder, "Post 2 content"),
                            )
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 0
                }

                expect("Comment to Persona") {
                    val spec =
                        Comment::persona.joinWithPredicates(joinType = JoinType.LEFT) { personaJoin, criteriaBuilder ->
                            listOf(
                                Persona::name.equal(personaJoin, criteriaBuilder, "Bob Johnson"),
                                Persona::age.equal(personaJoin, criteriaBuilder, 45),
                            )
                        }

                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 0
                }
            }
            context("right join") {
                expect("Comment to Post") {
                    val spec =
                        Comment::post.joinWithPredicates(joinType = JoinType.RIGHT) { postJoin, criteriaBuilder ->
                            listOf(
                                Post::title.equal(postJoin, criteriaBuilder, "John's Second Post"),
                                Post::content.equal(postJoin, criteriaBuilder, "Post 1 content"),
                            )
                        }
                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 0
                }

                expect("Comment to Persona") {
                    val spec =
                        Comment::persona.joinWithPredicates(joinType = JoinType.RIGHT) { personaJoin, criteriaBuilder ->
                            listOf(
                                Persona::name.equal(personaJoin, criteriaBuilder, "John Doe"),
                                Persona::age.equal(personaJoin, criteriaBuilder, 40),
                            )
                        }

                    val result = commentRepository.findAll(spec)
                    result shouldHaveSize 0
                }
            }
        }
    })
