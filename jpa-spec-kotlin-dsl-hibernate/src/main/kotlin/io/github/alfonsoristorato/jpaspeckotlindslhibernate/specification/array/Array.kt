package io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.array

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array.arrayNotContains
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that tests whether a native array column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Array].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the array.
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
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the array.
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
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the array.
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
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @param value the element value to check for non-containment.
 * @return A [Specification] that tests whether the element is not contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E> NestedProperty<ROOT, Array<E>>.arrayNotContains(value: E): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayNotContains(root, criteriaBuilder, value)
    }
