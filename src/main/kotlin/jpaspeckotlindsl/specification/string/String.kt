package jpaspeckotlindsl.specification.string

import jpaspeckotlindsl.predicate.string.like
import jpaspeckotlindsl.predicate.string.notLike
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property matches the given pattern.
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property matches the pattern.
 */
fun <T : Any> KProperty1<T, String>.like(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        like(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the property matches the given pattern (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property matches the pattern.
 */
@JvmName("likeNullable")
fun <T : Any> KProperty1<T, String?>.like(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        like(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the property does not match the given pattern.
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property does not match the pattern.
 */
fun <T : Any> KProperty1<T, String>.notLike(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notLike(root, criteriaBuilder, pattern)
    }

/**
 * Creates a [Specification] that checks if the property does not match the given pattern (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param pattern – The pattern to match against.
 * @return A [Specification] that checks if the property does not match the pattern.
 */
@JvmName("notLikeNullable")
fun <T : Any> KProperty1<T, String?>.notLike(pattern: String): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notLike(root, criteriaBuilder, pattern)
    }
