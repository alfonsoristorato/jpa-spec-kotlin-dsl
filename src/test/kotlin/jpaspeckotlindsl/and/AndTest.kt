package jpaspeckotlindsl.and

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jpaspeckotlindsl.jpasetup.JpaSetupApplication
import jpaspeckotlindsl.jpasetup.entity.User
import jpaspeckotlindsl.jpasetup.repository.UserRepository
import jpaspeckotlindsl.util.TestFixtures
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.Specification

@SpringBootTest(classes = [JpaSetupApplication::class])
class AndTest(
    private val userRepository: UserRepository,
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
        }

        expect("and combines specifications") {
            // TODO: change below to new DSL once implemented
            val idLowerThan3Spec =
                Specification<User> { root, _, criteriaBuilder ->
                    criteriaBuilder.lessThan(
                        root.get(User::id.name),
                        3L,
                    )
                }
            // TODO: change below to new DSL once implemented
            val lastNameEqualsSpec =
                Specification<User> { root, _, criteriaBuilder ->
                    criteriaBuilder.equal(
                        root.get<String>(User::lastName.name),
                        "LastName",
                    )
                }
            val result = userRepository.findAll(idLowerThan3Spec and lastNameEqualsSpec)
            assertSoftly {
                result shouldHaveSize 2
                result[0].name shouldBe "User 1"
                result[1].name shouldBe "User 2"
            }
        }
    })
