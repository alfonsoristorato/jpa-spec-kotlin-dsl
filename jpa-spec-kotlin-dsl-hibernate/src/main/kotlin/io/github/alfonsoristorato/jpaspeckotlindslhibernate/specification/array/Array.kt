package io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.array

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayIncludes
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayIntersects
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotIncludes
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotIntersects
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that tests whether a native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param value the element value to check for containment.
 * @return A [Specification] that tests whether the element is contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayContains(value: E): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether a native array column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param value the element value to check for non-containment.
 * @return A [Specification] that tests whether the element is not contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayNotContains(value: E): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayNotContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether a nested native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param value the element value to check for containment.
 * @return A [Specification] that tests whether the element is contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayContains(value: E): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether a nested native array column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param value the element value to check for non-containment.
 * @return A [Specification] that tests whether the element is not contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotContains(value: E): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayNotContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether a native array column contains all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must all be present in the column.
 * @return A [Specification] that tests whether all elements of the sub-array are contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayIncludes(subArray: Array<E>): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayIncludes(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a native array column does not contain all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must not all be present in the column.
 * @return A [Specification] that tests whether not all elements of the sub-array are contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayNotIncludes(subArray: Array<E>): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayNotIncludes(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a nested native array column contains all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must all be present in the column.
 * @return A [Specification] that tests whether all elements of the sub-array are contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayIncludes(subArray: Array<E>): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayIncludes(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a nested native array column does not contain all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must not all be present in the column.
 * @return A [Specification] that tests whether not all elements of the sub-array are contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotIncludes(subArray: Array<E>): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayNotIncludes(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a native array column shares at least one element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [Specification] that tests whether the native array column intersects with the given sub-array.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayIntersects(subArray: Array<E>): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayIntersects(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a native array column does not share any element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [Specification] that tests whether the native array column does not intersect with the given sub-array.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayNotIntersects(subArray: Array<E>): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayNotIntersects(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a nested native array column shares at least one element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [Specification] that tests whether the nested native array column intersects with the given sub-array.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayIntersects(subArray: Array<E>): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayIntersects(root, criteriaBuilder, subArray)
    }

/**
 * Creates a [Specification] that tests whether a nested native array column does not share any element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [Specification] that tests whether the nested native array column does not intersect with the given sub-array.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotIntersects(subArray: Array<E>): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayNotIntersects(root, criteriaBuilder, subArray)
    }
