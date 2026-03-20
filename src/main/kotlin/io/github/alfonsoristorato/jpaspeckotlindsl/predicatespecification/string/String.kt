package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.string.like
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.string.notLike
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given pattern.
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<T, String>.like(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        like(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given pattern (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
@JvmName("likeNullable")
fun <T : Any> KProperty1<T, String?>.like(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        like(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given pattern.
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<T, String>.notLike(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notLike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given pattern (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
@JvmName("notLikeNullable")
fun <T : Any> KProperty1<T, String?>.notLike(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notLike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property matches the given pattern.
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.like(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        like(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property matches the given pattern.
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
@JvmName("nestedLikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.like(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        like(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property does not match the given pattern.
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.notLike(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notLike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property does not match the given pattern.
 *
 * @receiver [ROOT] – the root entity type.
 * @param pattern – The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotLikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.notLike(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notLike(from, criteriaBuilder, pattern)
    }
