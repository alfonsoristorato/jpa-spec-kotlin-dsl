package jpaspeckotlindsl.predicate.string

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property matches the given pattern.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
fun <T> KProperty1<T, String>.like(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.like(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property matches the given pattern (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property matches the pattern.
 */
@JvmName("likeNullable")
fun <T> KProperty1<T, String?>.like(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.like(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given pattern.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
fun <T> KProperty1<T, String>.notLike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.notLike(path.get(this.name), pattern)

/**
 * Creates a [Predicate] that checks if the property does not match the given pattern (nullable version).
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param pattern The pattern to match against.
 * @return A [Predicate] that checks if the property does not match the pattern.
 */
@JvmName("notLikeNullable")
fun <T> KProperty1<T, String?>.notLike(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    pattern: String,
): Predicate = criteriaBuilder.notLike(path.get(this.name), pattern)
