package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.resolveHibernateCriteriaBuilder
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property matches the given pattern, ignoring case sensitivity.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
fun <T> KProperty1<T, String>.ilike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilike(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property matches the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
@JvmName("ilikeNullable")
fun <T> KProperty1<T, String?>.ilike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilike(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given pattern, ignoring case sensitivity.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
fun <T> KProperty1<T, String>.notIlike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlike(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
@JvmName("notIlikeNullable")
fun <T> KProperty1<T, String?>.notIlike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlike(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the nested String property matches the given pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.ilike(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilike(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested nullable String property matches the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
@JvmName("nestedIlikeNullable")
fun <ROOT> NestedProperty<ROOT, String?>.ilike(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilike(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested String property does not match the given pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.notIlike(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlike(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested nullable String property does not match the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotIlikeNullable")
fun <ROOT> NestedProperty<ROOT, String?>.notIlike(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlike(resolve(path), pattern)
