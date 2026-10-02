package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.combiner

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate

/**
 * ANDs all the given [Predicate]s together.
 *
 * @param criteriaBuilder The criteria builder.
 * @param predicate The [Predicate]s to combine.
 * @return The conjunction of all predicates.
 */
fun and(
    criteriaBuilder: CriteriaBuilder,
    vararg predicate: Predicate,
): Predicate = criteriaBuilder.and(*predicate)

/**
 * ORs all the given [Predicate]s together.
 *
 * @param criteriaBuilder The criteria builder.
 * @param predicate The [Predicate]s to combine.
 * @return The disjunction of all predicates.
 */
fun or(
    criteriaBuilder: CriteriaBuilder,
    vararg predicate: Predicate,
): Predicate = criteriaBuilder.or(*predicate)
