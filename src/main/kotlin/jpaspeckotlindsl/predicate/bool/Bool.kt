package jpaspeckotlindsl.predicate.bool

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the Boolean property is true.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the property is true.
 */
fun <T> KProperty1<T, Boolean>.isTrue(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isTrue(path.get(this.name))

/**
 * Creates a [Predicate] that checks if the nullable Boolean property is true.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the property is true.
 */
@JvmName("isTrueNullable")
fun <T> KProperty1<T, Boolean?>.isTrue(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isTrue(path.get(this.name))

/**
 * Creates a [Predicate] that checks if the Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the property is false.
 */
fun <T> KProperty1<T, Boolean>.isFalse(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isFalse(path.get(this.name))

/**
 * Creates a [Predicate] that checks if the nullable Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the property is false.
 */
@JvmName("isFalseNullable")
fun <T> KProperty1<T, Boolean?>.isFalse(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isFalse(path.get(this.name))
