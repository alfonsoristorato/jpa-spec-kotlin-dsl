package io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal

import jakarta.persistence.criteria.CriteriaBuilder
import org.hibernate.query.criteria.HibernateCriteriaBuilder

fun CriteriaBuilder.resolveHibernateCriteriaBuilder(): HibernateCriteriaBuilder =
    this as? HibernateCriteriaBuilder
        ?: throw IllegalStateException(
            "jpa-spec-kotlin-dsl-hibernate requires Hibernate as the JPA provider.",
        )
