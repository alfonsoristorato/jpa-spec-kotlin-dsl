package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property is equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is equal to the given value.
 */
fun <T, P> KProperty1<T, P>.equal(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.equal(path.get<P>(this.name), value)

/**
 * Creates a [Predicate] that checks if the property is not equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is not equal to the given value.
 */
fun <T, P> KProperty1<T, P>.notEqual(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.notEqual(path.get<P>(this.name), value)

/**
 * Creates a [Predicate] that checks if the nested property is equal to the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the nested property is equal to the given value.
 */
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.equal(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.equal(resolve(path), value)

/**
 * Creates a [Predicate] that checks if the nested property is not equal to the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the nested property is not equal to the given value.
 */
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.notEqual(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.notEqual(resolve(path), value)
