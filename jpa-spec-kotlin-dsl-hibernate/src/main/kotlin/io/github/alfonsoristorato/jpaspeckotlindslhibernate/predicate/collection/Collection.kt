package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.resolveHibernateCriteriaBuilder
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that tests whether a native collection column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the collection.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for containment.
 * @return A [Predicate] that tests whether the element is contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.collectionContains(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .collectionContains(path.get(this.name), value)

/**
 * Creates a [Predicate] that tests whether a native collection column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the collection.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-containment.
 * @return A [Predicate] that tests whether the element is not contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.collectionNotContains(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(collectionContains(path.get(this@collectionNotContains.name), value))
    }

/**
 * Creates a [Predicate] that tests whether a nested native collection column contains a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the collection.
 * @param PROP the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for containment.
 * @return A [Predicate] that tests whether the element is contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .collectionContains(resolve(path), value)

/**
 * Creates a [Predicate] that tests whether a nested native collection column does not contain a given element.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the collection.
 * @param PROP the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-containment.
 * @return A [Predicate] that tests whether the element is not contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionNotContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(collectionContains(resolve(path), value))
    }

/**
 * Creates a [Predicate] that tests whether a native collection column contains all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the collection.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection whose elements must all be present in the column.
 * @return A [Predicate] that tests whether all elements of the sub-collection are contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.collectionIncludes(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .collectionIncludes(path.get(this.name), subCollection)

/**
 * Creates a [Predicate] that tests whether a native collection column does not contain all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the collection.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection whose elements must not all be present in the column.
 * @return A [Predicate] that tests whether not all elements of the sub-collection are contained in the native collection column.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.collectionNotIncludes(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(collectionIncludes(path.get(this@collectionNotIncludes.name), subCollection))
    }

/**
 * Creates a [Predicate] that tests whether a nested native collection column contains all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the collection.
 * @param PROP the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection whose elements must all be present in the column.
 * @return A [Predicate] that tests whether all elements of the sub-collection are contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionIncludes(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .collectionIncludes(resolve(path), subCollection)

/**
 * Creates a [Predicate] that tests whether a nested native collection column does not contain all elements of the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the collection.
 * @param PROP the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection whose elements must not all be present in the column.
 * @return A [Predicate] that tests whether not all elements of the sub-collection are contained in the nested native collection column.
 */
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionNotIncludes(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(collectionIncludes(resolve(path), subCollection))
    }

/**
 * Creates a [Predicate] that tests whether a native collection column shares at least one element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the collection.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection to check for overlap.
 * @return A [Predicate] that tests whether the native collection column intersects with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.collectionIntersects(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .collectionIntersects(path.get(this.name), subCollection)

/**
 * Creates a [Predicate] that tests whether a native collection column does not share any element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param T the type of the entity.
 * @param E the type of the element in the collection.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection to check for overlap.
 * @return A [Predicate] that tests whether the native collection column does not intersect with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <T, E> KProperty1<T, Collection<E>>.collectionNotIntersects(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(collectionIntersects(path.get(this@collectionNotIntersects.name), subCollection))
    }

/**
 * Creates a [Predicate] that tests whether a nested native collection column shares at least one element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the collection.
 * @param PROP the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection to check for overlap.
 * @return A [Predicate] that tests whether the nested native collection column intersects with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionIntersects(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder
        .resolveHibernateCriteriaBuilder()
        .collectionIntersects(resolve(path), subCollection)

/**
 * Creates a [Predicate] that tests whether a nested native collection column does not share any element with the given sub-collection.
 * Use this for properties mapped with [@JdbcTypeCode(SqlTypes.ARRAY)][org.hibernate.annotations.JdbcTypeCode]
 * and typed as [Collection].
 *
 * @param ROOT the root entity type.
 * @param E the type of the element in the collection.
 * @param PROP the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param subCollection The sub-collection to check for overlap.
 * @return A [Predicate] that tests whether the nested native collection column does not intersect with the given sub-collection.
 */
@ExperimentalHibernateApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.collectionNotIntersects(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    subCollection: Collection<E>,
): Predicate =
    criteriaBuilder.resolveHibernateCriteriaBuilder().run {
        not(collectionIntersects(resolve(path), subCollection))
    }
