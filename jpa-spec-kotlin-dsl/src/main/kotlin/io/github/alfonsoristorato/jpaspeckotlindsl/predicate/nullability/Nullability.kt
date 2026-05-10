package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.nullability

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property is null.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the property is null.
 */
fun <T, P> KProperty1<T, P>.isNull(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isNull(path.get<P>(this.name))

/**
 * Creates a [Predicate] that checks if the property is not null.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the property is not null.
 */
fun <T, P> KProperty1<T, P>.isNotNull(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isNotNull(path.get<P>(this.name))

/**
 * Creates a [Predicate] that checks if the nested property is null.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the nested property is null.
 */
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.isNull(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isNull(resolve(path))

/**
 * Creates a [Predicate] that checks if the nested property is not null.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the nested property is not null.
 */
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.isNotNull(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isNotNull(resolve(path))
