package jpaspeckotlindsl.specification.comparison

import jpaspeckotlindsl.predicate.comparison.between
import jpaspeckotlindsl.predicate.comparison.greaterThan
import jpaspeckotlindsl.predicate.comparison.greaterThanOrEqualTo
import jpaspeckotlindsl.predicate.comparison.lessThan
import jpaspeckotlindsl.predicate.comparison.lessThanOrEqualTo
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property is greater than the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [Specification] that checks if the property is greater than the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P>.greaterThan(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        greaterThan(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the property is greater than or equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [Specification] that checks if the property is greater than or equal to the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P>.greaterThanOrEqualTo(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        greaterThanOrEqualTo(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the property is less than the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [Specification] that checks if the property is less than the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P>.lessThan(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        lessThan(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the property is less than or equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param value – the value to compare against.
 * @return A [Specification] that checks if the property is less than or equal to the given value.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P>.lessThanOrEqualTo(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        lessThanOrEqualTo(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the property is between two values (inclusive).
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the properties, which must be [Comparable].
 * @param lower – the lower bound value (inclusive).
 * @param upper – the upper bound value (inclusive).
 * @return A [Specification] that checks if the property is between the two values.
 */
fun <T : Any, P : Comparable<P>> KProperty1<T, P>.between(
    lower: P,
    upper: P,
): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        between(root, criteriaBuilder, lower, upper)
    }
