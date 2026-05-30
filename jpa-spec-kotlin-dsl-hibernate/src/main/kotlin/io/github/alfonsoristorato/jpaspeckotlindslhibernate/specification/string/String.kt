package io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.ilike
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.notIlike
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property matches the given pattern, ignoring case sensitivity.
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<T, String>.ilike(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        ilike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the property matches the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property matches the pattern.
 */
@JvmName("ilikeNullable")
fun <T : Any> KProperty1<T, String?>.ilike(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        ilike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the property does not match the given pattern, ignoring case sensitivity.
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<T, String>.notIlike(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notIlike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the property does not match the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property does not match the pattern.
 */
@JvmName("notIlikeNullable")
fun <T : Any> KProperty1<T, String?>.notIlike(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notIlike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the nested String property matches the given pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the nested property matches the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.ilike(pattern: String): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        ilike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the nested nullable String property matches the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the nested property matches the pattern.
 */
@JvmName("nestedIlikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.ilike(pattern: String): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        ilike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the nested String property does not match the given pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the nested property does not match the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.notIlike(pattern: String): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        notIlike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the nested nullable String property does not match the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotIlikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.notIlike(pattern: String): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        notIlike(root, criteriaBuilder, pattern)
    }
