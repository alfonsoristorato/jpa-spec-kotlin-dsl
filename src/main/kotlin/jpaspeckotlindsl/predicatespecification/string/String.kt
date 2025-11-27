package jpaspeckotlindsl.predicatespecification.string

import jpaspeckotlindsl.predicate.string.like
import jpaspeckotlindsl.predicate.string.notLike
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
