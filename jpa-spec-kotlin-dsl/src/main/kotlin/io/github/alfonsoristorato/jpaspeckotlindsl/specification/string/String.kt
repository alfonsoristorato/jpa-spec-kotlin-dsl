package io.github.alfonsoristorato.jpaspeckotlindsl.specification.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.string.like
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.string.notLike
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property matches the given pattern.
 *
 * @param T the type of the entity.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<in T, String>.like(
    pattern: String,
    escapeChar: Char? = null,
): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        like(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the property matches the given pattern (nullable version).
 *
 * @param T the type of the entity.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the property matches the pattern.
 */
@JvmName("likeNullable")
fun <T : Any> KProperty1<in T, String?>.like(
    pattern: String,
    escapeChar: Char? = null,
): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        like(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the property does not match the given pattern.
 *
 * @param T the type of the entity.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<in T, String>.notLike(
    pattern: String,
    escapeChar: Char? = null,
): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notLike(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the property does not match the given pattern (nullable version).
 *
 * @param T the type of the entity.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the property does not match the pattern.
 */
@JvmName("notLikeNullable")
fun <T : Any> KProperty1<in T, String?>.notLike(
    pattern: String,
    escapeChar: Char? = null,
): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notLike(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the nested String property matches the given pattern.
 *
 * @param ROOT the root entity type.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the nested property matches the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.like(
    pattern: String,
    escapeChar: Char? = null,
): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        like(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the nested nullable String property matches the given pattern.
 *
 * @param ROOT the root entity type.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the nested property matches the pattern.
 */
@JvmName("nestedLikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.like(
    pattern: String,
    escapeChar: Char? = null,
): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        like(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the nested String property does not match the given pattern.
 *
 * @param ROOT the root entity type.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the nested property does not match the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.notLike(
    pattern: String,
    escapeChar: Char? = null,
): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        notLike(root, criteriaBuilder, pattern, escapeChar)
    }

/**
 * Creates a [Specification] that checks if the nested nullable String property does not match the given pattern.
 *
 * @param ROOT the root entity type.
 * @param pattern The pattern to match against.
 * @param escapeChar Optional character that escapes `%`, `_` and itself in [pattern], so they match literally.
 * @return A [Specification] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotLikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.notLike(
    pattern: String,
    escapeChar: Char? = null,
): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        notLike(root, criteriaBuilder, pattern, escapeChar)
    }
