package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import org.hibernate.query.criteria.HibernateCriteriaBuilder
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that tests whether an element is contained in a native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the array.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for containment.
 * @return A [Predicate] that tests whether an element is contained in the native array column.
 */
@Suppress("UNCHECKED_CAST")
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.arrayContains(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    (criteriaBuilder as HibernateCriteriaBuilder)
        .arrayContains(path.get<T>(this.name) as Expression<Array<E>>, value)

/**
 * Creates a [Predicate] that tests whether an element is not contained in a native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the array.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-containment.
 * @return A [Predicate] that tests whether an element is not contained in the native array column.
 */
@Suppress("UNCHECKED_CAST")
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.arrayNotContains(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    (criteriaBuilder as HibernateCriteriaBuilder).run {
        not(arrayContains(path.get<T>(this@arrayNotContains.name) as Expression<Array<E>>, value))
    }

/**
 * Creates a [Predicate] that tests whether an element is contained in a nested native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for containment.
 * @return A [Predicate] that tests whether an element is contained in the nested native array column.
 */
@Suppress("UNCHECKED_CAST")
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.arrayContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    (criteriaBuilder as HibernateCriteriaBuilder)
        .arrayContains(resolve(path) as Expression<Array<E>>, value)

/**
 * Creates a [Predicate] that tests whether an element is not contained in a nested native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-containment.
 * @return A [Predicate] that tests whether an element is not contained in the nested native array column.
 */
@Suppress("UNCHECKED_CAST")
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.arrayNotContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    (criteriaBuilder as HibernateCriteriaBuilder).run {
        not(arrayContains(resolve(path) as Expression<Array<E>>, value))
    }
