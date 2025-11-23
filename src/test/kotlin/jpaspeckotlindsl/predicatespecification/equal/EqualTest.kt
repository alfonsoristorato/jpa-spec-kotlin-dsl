package jpaspeckotlindsl.predicatespecification.equal

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jpaspeckotlindsl.jpasetup.JpaSetupApplication
import jpaspeckotlindsl.jpasetup.entity.Post
import jpaspeckotlindsl.jpasetup.entity.User
import jpaspeckotlindsl.jpasetup.repository.PostRepository
import jpaspeckotlindsl.jpasetup.repository.UserRepository
import jpaspeckotlindsl.util.TestFixtures
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [JpaSetupApplication::class])
class EqualTest(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) : ExpectSpec({
        beforeSpec {
            val user1 =
                TestFixtures.createUser(
                    name = "User 1",
                )
            val user2 =
                TestFixtures.createUser(
                    name = "User 2",
                )
            val user3 =
                TestFixtures.createUser(
                    name = "User 3",
                )
            userRepository.saveAll(listOf(user1, user2, user3))
            userRepository.findAll() shouldHaveSize 3

            val post1User1 =
                TestFixtures.createPost(
                    title = "Post 1",
                    user = user1,
                )
            val post2User1 =
                TestFixtures.createPost(
                    title = "Post 2",
                    user = user1,
                )
            val post1User2 =
                TestFixtures.createPost(
                    title = "Post 2",
                    user = user2,
                )
            val post1User3 =
                TestFixtures.createPost(
                    title = "Post 3",
                    user = user3,
                )
            postRepository.saveAll(listOf(post1User1, post2User1, post1User2, post1User3))
            postRepository.findAll() shouldHaveSize 4
        }

        context("equal for PredicateSpecification checks for equality") {
            expect("with declared types") {
                val user1 = userRepository.findAll().first { it.name == "User 1" }
                val postsWithSpecificUser = Post::user.equal(user1)
                val result =
                    postRepository.findAll(postsWithSpecificUser)
                result shouldHaveSize 2
                result[0].title shouldBe "Post 1"
                result[1].title shouldBe "Post 2"
            }
            expect("with primitive types") {
                val postsWithSpecificTitle = Post::title.equal("Post 2")
                val result1 =
                    postRepository.findAll(postsWithSpecificTitle)
                result1 shouldHaveSize 2
                result1[0].user.name shouldBe "User 1"
                result1[1].user.name shouldBe "User 2"

                val usersWithSpecificAge = User::age.equal(TestFixtures.DEFAULT_USER_AGE)
                val result2 =
                    userRepository.findAll(usersWithSpecificAge)
                result2 shouldHaveSize 3
                result2[0].name shouldBe "User 1"
                result2[1].name shouldBe "User 2"
                result2[2].name shouldBe "User 3"
            }
        }
    })
