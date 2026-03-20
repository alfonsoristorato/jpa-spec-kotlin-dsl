package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property's value is in the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to check for inclusion (can be a [Collection]).
 * @return A [Predicate] that checks if the property is in the value.
 */
fun <T, P> KProperty1<T, P>.`in`(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.`in`(path.get<P>(this.name)).value(value)

/**
 * Creates a [Predicate] that checks if the nested property's value is in the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to check for inclusion (can be a [Collection]).
 * @return A [Predicate] that checks if the nested property is in the value.
 */
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.`in`(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.`in`(resolve(path)).value(value)
