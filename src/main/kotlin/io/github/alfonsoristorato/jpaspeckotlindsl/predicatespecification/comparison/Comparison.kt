package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.comparison

import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.between
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.greaterThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.greaterThanOrEqualTo
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.lessThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.lessThanOrEqualTo
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the property is greater than the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value the value to compare against.
 * @return A [PredicateSpecification] that checks if the property is greater than the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P?>.greaterThan(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        greaterThan(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property is greater than or equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks if the property is greater than or equal to the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P?>.greaterThanOrEqualTo(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        greaterThanOrEqualTo(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property is less than the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks if the property is less than the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P?>.lessThan(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        lessThan(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property is less than or equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks if the property is less than or equal to the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P?>.lessThanOrEqualTo(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        lessThanOrEqualTo(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property is between two values (inclusive).
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the properties, which must be [Comparable].
 * @param lower – the lower bound value (inclusive).
 * @param upper – the upper bound value (inclusive).
 * @return A [PredicateSpecification] that checks if the property is between the two values.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P?>.between(
    lower: P,
    upper: P,
): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        between(from, criteriaBuilder, lower, upper)
    }
