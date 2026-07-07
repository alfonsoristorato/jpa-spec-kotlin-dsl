package io.github.alfonsoristorato.jpaspeckotlindsl.specification.inclusion

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion.`in`
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion.notIn
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property's value is in the given value.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [Specification] that checks if the property is in the value.
 */
fun <T : Any, P> KProperty1<T, P>.`in`(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        `in`(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the property's value is in the given value.
 * This delegates to the [`in`] method.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [Specification] that checks if the property is in the value.
 */
fun <T : Any, P> KProperty1<T, P>.containedIn(value: P): Specification<T> = `in`(value)

/**
 * Creates a [Specification] that checks if the nested property's value is in the given value.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [Specification] that checks if the nested property is in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.`in`(value: PROP): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        `in`(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the nested property's value is in the given value.
 * This delegates to the [`in`] method.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [Specification] that checks if the nested property is in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.containedIn(value: PROP): Specification<ROOT> = `in`(value)

/**
 * Creates a [Specification] that checks if the property's value is not in the given value.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [Specification] that checks if the property is not in the value.
 */
fun <T : Any, P> KProperty1<T, P>.notIn(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notIn(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the property's value is not in the given value.
 * This delegates to the [notIn] method.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [Specification] that checks if the property is not in the value.
 */
fun <T : Any, P> KProperty1<T, P>.notContainedIn(value: P): Specification<T> = notIn(value)

/**
 * Creates a [Specification] that checks if the nested property's value is not in the given value.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [Specification] that checks if the nested property is not in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.notIn(value: PROP): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        notIn(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks if the nested property's value is not in the given value.
 * This delegates to the [notIn] method.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [Specification] that checks if the nested property is not in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.notContainedIn(value: PROP): Specification<ROOT> = notIn(value)
