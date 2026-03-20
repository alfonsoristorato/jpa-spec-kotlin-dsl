package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.bool

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
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

/**
 * Creates a [Predicate] that checks if the nested Boolean property is true.
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the nested property is true.
 */
fun <ROOT> NestedProperty<ROOT, Boolean>.isTrue(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isTrue(resolve(path))

/**
 * Creates a [Predicate] that checks if the nested nullable Boolean property is true.
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the nested property is true.
 */
@JvmName("nestedIsTrueNullable")
fun <ROOT> NestedProperty<ROOT, Boolean?>.isTrue(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isTrue(resolve(path))

/**
 * Creates a [Predicate] that checks if the nested Boolean property is false.
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the nested property is false.
 */
fun <ROOT> NestedProperty<ROOT, Boolean>.isFalse(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isFalse(resolve(path))

/**
 * Creates a [Predicate] that checks if the nested nullable Boolean property is false.
 *
 * @receiver [ROOT] – the root entity type.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that checks if the nested property is false.
 */
@JvmName("nestedIsFalseNullable")
fun <ROOT> NestedProperty<ROOT, Boolean?>.isFalse(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isFalse(resolve(path))
