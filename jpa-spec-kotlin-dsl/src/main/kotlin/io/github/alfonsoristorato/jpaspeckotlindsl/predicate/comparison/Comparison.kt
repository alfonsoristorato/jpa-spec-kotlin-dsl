package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property is greater than the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is greater than the given value.
 */
fun <T, P : Comparable<P>> KProperty1<T, P?>.greaterThan(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.greaterThan(path.get(this.name), value)

/**
 * Creates a [Predicate] that checks if the property is greater than or equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is greater than or equal to the given value.
 */
fun <T, P : Comparable<P>> KProperty1<T, P?>.greaterThanOrEqualTo(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.greaterThanOrEqualTo(path.get(this.name), value)

/**
 * Creates a [Predicate] that checks if the property is less than the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is less than the given value.
 */
fun <T, P : Comparable<P>> KProperty1<T, P?>.lessThan(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.lessThan(path.get(this.name), value)

/**
 * Creates a [Predicate] that checks if the property is less than or equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is less than or equal to the given value.
 */
fun <T, P : Comparable<P>> KProperty1<T, P?>.lessThanOrEqualTo(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.lessThanOrEqualTo(path.get(this.name), value)

/**
 * Creates a [Predicate] that checks if the property is between two values (inclusive).
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property, which must be [Comparable].
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param lower The lower bound value (inclusive).
 * @param upper The upper bound value (inclusive).
 * @return A [Predicate] that checks if the property is between the two values.
 */
fun <T, P : Comparable<P>> KProperty1<T, P?>.between(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    lower: P,
    upper: P,
): Predicate = criteriaBuilder.between(path.get(this.name), lower, upper)

/**
 * Creates a [Predicate] that checks if the nested property is greater than the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property, which must be [Comparable].
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the nested property is greater than the given value.
 */
fun <ROOT, PROP : Comparable<PROP>> NestedProperty<ROOT, PROP>.greaterThan(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.greaterThan(resolve(path), value)

/**
 * Creates a [Predicate] that checks if the nested property is greater than or equal to the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property, which must be [Comparable].
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the nested property is greater than or equal to the given value.
 */
fun <ROOT, PROP : Comparable<PROP>> NestedProperty<ROOT, PROP>.greaterThanOrEqualTo(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.greaterThanOrEqualTo(resolve(path), value)

/**
 * Creates a [Predicate] that checks if the nested property is less than the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property, which must be [Comparable].
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the nested property is less than the given value.
 */
fun <ROOT, PROP : Comparable<PROP>> NestedProperty<ROOT, PROP>.lessThan(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.lessThan(resolve(path), value)

/**
 * Creates a [Predicate] that checks if the nested property is less than or equal to the given value.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property, which must be [Comparable].
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the nested property is less than or equal to the given value.
 */
fun <ROOT, PROP : Comparable<PROP>> NestedProperty<ROOT, PROP>.lessThanOrEqualTo(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: PROP,
): Predicate = criteriaBuilder.lessThanOrEqualTo(resolve(path), value)

/**
 * Creates a [Predicate] that checks if the nested property is between two values (inclusive).
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property, which must be [Comparable].
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param lower The lower bound value (inclusive).
 * @param upper The upper bound value (inclusive).
 * @return A [Predicate] that checks if the nested property is between the two values.
 */
fun <ROOT, PROP : Comparable<PROP>> NestedProperty<ROOT, PROP>.between(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    lower: PROP,
    upper: PROP,
): Predicate = criteriaBuilder.between(resolve(path), lower, upper)
