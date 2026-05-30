package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicatespecification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionNotContains
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that tests whether a native collection column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the collection.
 * @param value the element value to check for containment.
 * @return A [PredicateSpecification] that tests whether the element is contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.collectionContains(value: E): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native collection column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the collection.
 * @param value the element value to check for non-containment.
 * @return A [PredicateSpecification] that tests whether the element is not contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.collectionNotContains(value: E): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionNotContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native collection column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the collection.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param value the element value to check for containment.
 * @return A [PredicateSpecification] that tests whether the element is contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionContains(value: E): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionContains(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native collection column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the collection.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param value the element value to check for non-containment.
 * @return A [PredicateSpecification] that tests whether the element is not contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionNotContains(value: E): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionNotContains(from, criteriaBuilder, value)
    }
