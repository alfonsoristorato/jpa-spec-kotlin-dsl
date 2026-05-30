package io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal

import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.testconfig.SpringBootTestEnhanced
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.CriteriaBuilder
import org.hibernate.query.criteria.HibernateCriteriaBuilder
import java.lang.reflect.Proxy

@SpringBootTestEnhanced
class HibernateCriteriaBuilderUtilTest(
    private val entityManager: EntityManager,
) : ExpectSpec({
        context("resolveHibernateCriteriaBuilder") {
            expect("returns HibernateCriteriaBuilder when CriteriaBuilder is a HibernateCriteriaBuilder") {
                val criteriaBuilder = entityManager.criteriaBuilder
                val result = criteriaBuilder.resolveHibernateCriteriaBuilder()
                result.shouldBeInstanceOf<HibernateCriteriaBuilder>()
            }

            expect("throws IllegalStateException when CriteriaBuilder is not a HibernateCriteriaBuilder") {
                val nonHibernateCriteriaBuilder =
                    Proxy.newProxyInstance(
                        CriteriaBuilder::class.java.classLoader,
                        arrayOf(CriteriaBuilder::class.java),
                    ) { _, _, _ -> null } as CriteriaBuilder

                val exception =
                    shouldThrow<IllegalStateException> {
                        nonHibernateCriteriaBuilder.resolveHibernateCriteriaBuilder()
                    }
                exception.message shouldBe "jpa-spec-kotlin-dsl-hibernate requires Hibernate as the JPA provider."
            }
        }
    })
