package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion

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
