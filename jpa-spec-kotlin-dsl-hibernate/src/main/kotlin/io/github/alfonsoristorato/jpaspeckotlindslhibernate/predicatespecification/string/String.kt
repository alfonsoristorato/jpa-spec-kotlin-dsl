package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicatespecification.string

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.ilike
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.ilikeRegexp
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.likeRegexp
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.notIlike
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.notIlikeRegexp
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.string.notLikeRegexp
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given pattern, ignoring case sensitivity.
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<T, String>.ilike(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        ilike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
@JvmName("ilikeNullable")
fun <T : Any> KProperty1<T, String?>.ilike(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        ilike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given pattern, ignoring case sensitivity.
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<T, String>.notIlike(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
@JvmName("notIlikeNullable")
fun <T : Any> KProperty1<T, String?>.notIlike(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property matches the given pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.ilike(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        ilike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property matches the given pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
@JvmName("nestedIlikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.ilike(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        ilike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property does not match the given pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.notIlike(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property does not match the given pattern, ignoring case sensitivity (nullable version)..
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotIlikeNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.notIlike(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlike(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<T, String>.likeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        likeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
@JvmName("likeRegexpNullable")
fun <T : Any> KProperty1<T, String?>.likeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        likeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<T, String>.notLikeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notLikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
@JvmName("notLikeRegexpNullable")
fun <T : Any> KProperty1<T, String?>.notLikeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notLikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property matches the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.likeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        likeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property matches the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
@JvmName("nestedLikeRegexpNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.likeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        likeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property does not match the given POSIX regex pattern (case-sensitive).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.notLikeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notLikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property does not match the given POSIX regex pattern, case-sensitive (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotLikeRegexpNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.notLikeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notLikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<T, String>.ilikeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        ilikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property matches the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property matches the pattern.
 */
@JvmName("ilikeRegexpNullable")
fun <T : Any> KProperty1<T, String?>.ilikeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        ilikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<T, String>.notIlikeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property does not match the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [T] - the type of the entity.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the property does not match the pattern.
 */
@JvmName("notIlikeRegexpNullable")
fun <T : Any> KProperty1<T, String?>.notIlikeRegexp(pattern: String): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property matches the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.ilikeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        ilikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property matches the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property matches the pattern.
 */
@JvmName("nestedIlikeRegexpNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.ilikeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        ilikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested String property does not match the given POSIX regex pattern, ignoring case sensitivity.
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
fun <ROOT : Any> NestedProperty<ROOT, String>.notIlikeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlikeRegexp(from, criteriaBuilder, pattern)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested nullable String property does not match the given POSIX regex pattern, ignoring case sensitivity (nullable version).
 *
 * @receiver [ROOT] - the root entity type.
 * @param pattern The POSIX regex pattern to match against.
 * @return A [PredicateSpecification] that checks if the nested property does not match the pattern.
 */
@JvmName("nestedNotIlikeRegexpNullable")
fun <ROOT : Any> NestedProperty<ROOT, String?>.notIlikeRegexp(pattern: String): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notIlikeRegexp(from, criteriaBuilder, pattern)
    }
