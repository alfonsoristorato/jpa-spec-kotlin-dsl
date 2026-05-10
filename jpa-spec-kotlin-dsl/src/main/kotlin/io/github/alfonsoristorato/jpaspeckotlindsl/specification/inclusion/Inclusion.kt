package io.github.alfonsoristorato.jpaspeckotlindsl.specification.inclusion

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion.`in`
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property's value is in the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param value – the value to check for inclusion (can be a [Collection]).
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
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param value – the value to check for inclusion (can be a [Collection]).
 * @return A [Specification] that checks if the property is in the value.
 */
fun <T : Any, P> KProperty1<T, P>.containedIn(value: P): Specification<T> = `in`(value)

/**
 * Creates a [Specification] that checks if the nested property's value is in the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param value – the value to check for inclusion (can be a [Collection]).
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
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param value – the value to check for inclusion (can be a [Collection]).
 * @return A [Specification] that checks if the nested property is in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.containedIn(value: PROP): Specification<ROOT> = `in`(value)
