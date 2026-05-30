package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicatespecification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionIncludes
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionIntersects
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionNotContains
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionNotIncludes
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection.collectionNotIntersects
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

/**
 * Creates a [PredicateSpecification] that tests whether a native collection column contains all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the collection.
 * @param subCollection the sub-collection whose elements must all be present in the column.
 * @return A [PredicateSpecification] that tests whether all elements of the sub-collection are contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.collectionIncludes(subCollection: Collection<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionIncludes(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native collection column does not contain all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the collection.
 * @param subCollection the sub-collection whose elements must not all be present in the column.
 * @return A [PredicateSpecification] that tests whether not all elements of the sub-collection are contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.collectionNotIncludes(subCollection: Collection<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionNotIncludes(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native collection column contains all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the collection.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param subCollection the sub-collection whose elements must all be present in the column.
 * @return A [PredicateSpecification] that tests whether all elements of the sub-collection are contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionIncludes(
    subCollection: Collection<E>,
): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionIncludes(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native collection column does not contain all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the collection.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param subCollection the sub-collection whose elements must not all be present in the column.
 * @return A [PredicateSpecification] that tests whether not all elements of the sub-collection are contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionNotIncludes(
    subCollection: Collection<E>,
): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionNotIncludes(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native collection column shares at least one element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the collection.
 * @param subCollection the sub-collection to check for overlap.
 * @return A [PredicateSpecification] that tests whether the native collection column intersects with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.collectionIntersects(subCollection: Collection<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionIntersects(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a native collection column does not share any element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [T] - the type of the entity.
 * @receiver [E] - the type of the element in the collection.
 * @param subCollection the sub-collection to check for overlap.
 * @return A [PredicateSpecification] that tests whether the native collection column does not intersect with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <T : Any, E> KProperty1<T, Collection<E>>.collectionNotIntersects(subCollection: Collection<E>): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionNotIntersects(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native collection column shares at least one element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the collection.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param subCollection the sub-collection to check for overlap.
 * @return A [PredicateSpecification] that tests whether the nested native collection column intersects with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionIntersects(
    subCollection: Collection<E>,
): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionIntersects(from, criteriaBuilder, subCollection)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested native collection column does not share any element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @receiver [ROOT] - the root entity type.
 * @receiver [E] - the type of the element in the collection.
 * @receiver [PROP] - the type of the [Collection] property.
 * @param subCollection the sub-collection to check for overlap.
 * @return A [PredicateSpecification] that tests whether the nested native collection column does not intersect with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionNotIntersects(
    subCollection: Collection<E>,
): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        collectionNotIntersects(from, criteriaBuilder, subCollection)
    }
