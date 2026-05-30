package io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal

import jakarta.persistence.criteria.CriteriaBuilder
import org.hibernate.query.criteria.HibernateCriteriaBuilder

fun CriteriaBuilder.resolveHibernateCriteriaBuilder(): HibernateCriteriaBuilder =
    this as? HibernateCriteriaBuilder
        ?: throw IllegalStateException(
            "CriteriaBuilder is not an instance of HibernateCriteriaBuilder. Ensure that you are using Hibernate as the JPA provider.",
        )
