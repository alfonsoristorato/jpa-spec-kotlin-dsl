package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.path.path
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property matches the given pattern.
 *
 * @param T the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
fun <T> KProperty1<T, String>.like(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.like(path(path), pattern)
    } else {
        criteriaBuilder.like(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the property matches the given pattern (nullable version).
 *
 * @param T the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
@JvmName("likeNullable")
fun <T> KProperty1<T, String?>.like(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.like(path(path), pattern)
    } else {
        criteriaBuilder.like(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the property does not match the given pattern.
 *
 * @param T the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
fun <T> KProperty1<T, String>.notLike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.notLike(path(path), pattern)
    } else {
        criteriaBuilder.notLike(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the property does not match the given pattern (nullable version).
 *
 * @param T the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
@JvmName("notLikeNullable")
fun <T> KProperty1<T, String?>.notLike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.notLike(path(path), pattern)
    } else {
        criteriaBuilder.notLike(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the nested String property matches the given pattern.
 *
 * @param ROOT the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.like(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.like(path(path), pattern)
    } else {
        criteriaBuilder.like(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the nested nullable String property matches the given pattern (nullable version).
 *
 * @param ROOT the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
@JvmName("nestedLikeNullable")
fun <ROOT> NestedProperty<ROOT, String?>.like(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.like(path(path), pattern)
    } else {
        criteriaBuilder.like(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the nested String property does not match the given pattern.
 *
 * @param ROOT the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.notLike(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.notLike(path(path), pattern)
    } else {
        criteriaBuilder.notLike(path(path), pattern, escapeChar)
    }

/**
 * Creates a [Predicate] that checks if the nested nullable String property does not match the given pattern (nullable version).
 *
 * @param ROOT the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotLikeNullable")
fun <ROOT> NestedProperty<ROOT, String?>.notLike(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
    escapeChar: Char? = null,
): Predicate =
    if (escapeChar == null) {
        criteriaBuilder.notLike(path(path), pattern)
    } else {
        criteriaBuilder.notLike(path(path), pattern, escapeChar)
    }
