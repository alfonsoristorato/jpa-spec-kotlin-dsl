package io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.arrayContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.arrayNotContains
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that tests whether an element is contained in a native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the array.
 * @param value the element value to check for containment.
 * @return A [Specification] that tests whether an element is contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.arrayContains(value: E): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether an element is not contained in a native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the array.
 * @param value the element value to check for non-containment.
 * @return A [Specification] that tests whether an element is not contained in the native array column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.arrayNotContains(value: E): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        arrayNotContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether an element is contained in a nested native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param value the element value to check for containment.
 * @return A [Specification] that tests whether an element is contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.arrayContains(value: E): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayContains(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether an element is not contained in a nested native array column.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode].
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the array.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param value the element value to check for non-containment.
 * @return A [Specification] that tests whether an element is not contained in the nested native array column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.arrayNotContains(value: E): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        arrayNotContains(root, criteriaBuilder, value)
    }
