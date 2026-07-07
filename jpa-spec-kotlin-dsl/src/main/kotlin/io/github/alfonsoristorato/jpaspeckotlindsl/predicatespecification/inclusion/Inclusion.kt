package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.inclusion

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion.`in`
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion.notIn
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the property's value is in the given value.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the property is in the value.
 */
fun <T : Any, P> KProperty1<T, P>.`in`(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        `in`(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property's value is in the given value.
 * This delegates to the [`in`] method.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the property is in the value.
 */
fun <T : Any, P> KProperty1<T, P>.containedIn(value: P): PredicateSpecification<T> = `in`(value)

/**
 * Creates a [PredicateSpecification] that checks if the nested property's value is in the given value.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the nested property is in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.`in`(value: PROP): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        `in`(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested property's value is in the given value.
 * This delegates to the [`in`] method.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for inclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the nested property is in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.containedIn(value: PROP): PredicateSpecification<ROOT> = `in`(value)

/**
 * Creates a [PredicateSpecification] that checks if the property's value is not in the given value.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the property is not in the value.
 */
fun <T : Any, P> KProperty1<T, P>.notIn(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notIn(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property's value is not in the given value.
 * This delegates to the [notIn] method.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the property is not in the value.
 */
fun <T : Any, P> KProperty1<T, P>.notContainedIn(value: P): PredicateSpecification<T> = notIn(value)

/**
 * Creates a [PredicateSpecification] that checks if the nested property's value is not in the given value.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the nested property is not in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.notIn(value: PROP): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notIn(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested property's value is not in the given value.
 * This delegates to the [notIn] method.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param value the value to check for exclusion (can be a [Collection]).
 * @return A [PredicateSpecification] that checks if the nested property is not in the value.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.notContainedIn(value: PROP): PredicateSpecification<ROOT> = notIn(value)
