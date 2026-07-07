package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicatespecification.array

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayIncludes
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayIntersects
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotIncludes
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotIntersects
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that tests whether a native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param value the element value to check for containment.
 * @return A [PredicateSpecification] that tests whether the element is contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayContains(value: E): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native array column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param value the element value to check for non-containment.
 * @return A [PredicateSpecification] that tests whether the element is not contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayNotContains(value: E): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayNotContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param value the element value to check for containment.
 * @return A [PredicateSpecification] that tests whether the element is contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayContains(value: E): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native array column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param value the element value to check for non-containment.
 * @return A [PredicateSpecification] that tests whether the element is not contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotContains(value: E): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayNotContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native array column contains all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must all be present in the column.
 * @return A [PredicateSpecification] that tests whether all elements of the sub-array are contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayIncludes(subArray: Array<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayIncludes(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native array column does not contain all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must not all be present in the column.
 * @return A [PredicateSpecification] that tests whether not all elements of the sub-array are contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayNotIncludes(subArray: Array<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayNotIncludes(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native array column contains all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must all be present in the column.
 * @return A [PredicateSpecification] that tests whether all elements of the sub-array are contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayIncludes(subArray: Array<E>): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayIncludes(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native array column does not contain all elements of the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array whose elements must not all be present in the column.
 * @return A [PredicateSpecification] that tests whether not all elements of the sub-array are contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotIncludes(subArray: Array<E>): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayNotIncludes(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native array column shares at least one element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [PredicateSpecification] that tests whether the native array column intersects with the given sub-array.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayIntersects(subArray: Array<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayIntersects(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native array column does not share any element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [PredicateSpecification] that tests whether the native array column does not intersect with the given sub-array.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Array<E>>.arrayNotIntersects(subArray: Array<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayNotIntersects(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native array column shares at least one element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [PredicateSpecification] that tests whether the nested native array column intersects with the given sub-array.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayIntersects(subArray: Array<E>): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayIntersects(from, criteriaBuilder, subArray)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native array column does not share any element with the given sub-array.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the array.
 * @param subArray the sub-array to check for overlap.
 * @return A [PredicateSpecification] that tests whether the nested native array column does not intersect with the given sub-array.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotIntersects(subArray: Array<E>): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        arrayNotIntersects(from, criteriaBuilder, subArray)
    }
