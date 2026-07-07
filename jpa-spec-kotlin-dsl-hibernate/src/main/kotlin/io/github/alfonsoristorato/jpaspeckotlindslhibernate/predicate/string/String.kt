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
 * @receiver [T] - the type of the entity.
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
 * @receiver [T] - the type of the entity.
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
 * @receiver [T] - the type of the entity.
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
 * @receiver [T] - the type of the entity.
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
 * @receiver [ROOT] - the root entity type.
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
 * @receiver [ROOT] - the root entity type.
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
 * @receiver [ROOT] - the root entity type.
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
 * @receiver [ROOT] - the root entity type.
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

/**
 * Creates a [Predicate] that checks if the property matches the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
fun <T> KProperty1<T, String>.likeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().likeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property matches the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
@JvmName("likeRegexpNullable")
fun <T> KProperty1<T, String?>.likeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().likeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
fun <T> KProperty1<T, String>.notLikeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notLikeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
@JvmName("notLikeRegexpNullable")
fun <T> KProperty1<T, String?>.notLikeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notLikeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the nested String property matches the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.likeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().likeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested nullable String property matches the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
@JvmName("nestedLikeRegexpNullable")
fun <ROOT> NestedProperty<ROOT, String?>.likeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().likeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested String property does not match the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.notLikeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notLikeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested nullable String property does not match the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotLikeRegexpNullable")
fun <ROOT> NestedProperty<ROOT, String?>.notLikeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notLikeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the property matches the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
fun <T> KProperty1<T, String>.ilikeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilikeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property matches the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
@JvmName("ilikeRegexpNullable")
fun <T> KProperty1<T, String?>.ilikeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilikeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
fun <T> KProperty1<T, String>.notIlikeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlikeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
@JvmName("notIlikeRegexpNullable")
fun <T> KProperty1<T, String?>.notIlikeRegexp(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlikeRegexp(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the nested String property matches the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.ilikeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilikeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested nullable String property matches the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property matches the pattern.
 */
@JvmName("nestedIlikeRegexpNullable")
fun <ROOT> NestedProperty<ROOT, String?>.ilikeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().ilikeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested String property does not match the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
fun <ROOT> NestedProperty<ROOT, String>.notIlikeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlikeRegexp(resolve(path), pattern)

/**
 * Creates a [Predicate] that checks if the nested nullable String property does not match the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [Predicate] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotIlikeRegexpNullable")
fun <ROOT> NestedProperty<ROOT, String?>.notIlikeRegexp(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.resolveHibernateCriteriaBuilder().notIlikeRegexp(resolve(path), pattern)
