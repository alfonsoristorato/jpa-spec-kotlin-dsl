package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.resolveHibernateCriteriaBuilder
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that tests whether a native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the array.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for containment.
 * @return A [Predicate] that tests whether the element is contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Array<E>>.arrayContains(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .arrayContains(path.get(this.name), value)

/**
 * Creates a [Predicate] that tests whether a native array column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the array.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-containment.
 * @return A [Predicate] that tests whether the element is not contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Array<E>>.arrayNotContains(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(arrayContains(path.get(this@arrayNotContains.name), value))
    }

/**
 * Creates a [Predicate] that tests whether a nested native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for containment.
 * @return A [Predicate] that tests whether the element is contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT, E> NestedProperty<ROOT, Array<E>>.arrayContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .arrayContains(resolve(path), value)

/**
 * Creates a [Predicate] that tests whether a nested native array column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-containment.
 * @return A [Predicate] that tests whether the element is not contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT, E> NestedProperty<ROOT, Array<E>>.arrayNotContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(arrayContains(resolve(path), value))
    }
