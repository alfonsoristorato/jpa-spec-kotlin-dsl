package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.internal.ExperimentalApi
import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import org.hibernate.query.criteria.HibernateCriteriaBuilder
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that tests whether a collection is empty.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that tests whether a collection is empty.
 */
fun <T, E, P : Collection<E>> KProperty1<T, P>.isEmpty(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isEmpty(path.get(this.name))

/**
 * Creates a [Predicate] that tests whether a collection is not empty.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that tests whether a collection is not empty.
 */
fun <T, E, P : Collection<E>> KProperty1<T, P>.isNotEmpty(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isNotEmpty(path.get(this.name))

/**
 * Creates a [Predicate] that tests whether an element is a member of a collection.
 * If the collection is empty, the predicate will be false.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for membership.
 * @return A [Predicate] that tests whether an element is a member of a collection.
 * If the collection is empty, the predicate will be false.
 */
fun <T, E, P : Collection<E>> KProperty1<T, P>.isMember(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate = criteriaBuilder.isMember(value, path.get(this.name))

/**
 * Creates a [Predicate] that tests whether an element is not a member of a collection.
 * If the collection is empty, the predicate will be true.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-membership.
 * @return A [Predicate] that tests whether an element is not a member of a collection.
 * If the collection is empty, the predicate will be true.
 */
fun <T, E, P : Collection<E>> KProperty1<T, P>.isNotMember(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate = criteriaBuilder.isNotMember(value, path.get(this.name))

/**
 * Creates a [Predicate] that tests whether a nested collection is empty.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that tests whether the nested collection is empty.
 */
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isEmpty(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isEmpty(resolve(path))

/**
 * Creates a [Predicate] that tests whether a nested collection is not empty.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @return A [Predicate] that tests whether the nested collection is not empty.
 */
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isNotEmpty(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
): Predicate = criteriaBuilder.isNotEmpty(resolve(path))

/**
 * Creates a [Predicate] that tests whether an element is a member of a nested collection.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for membership.
 * @return A [Predicate] that tests whether an element is a member of the nested collection.
 */
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isMember(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate = criteriaBuilder.isMember(value, resolve(path))

/**
 * Creates a [Predicate] that tests whether an element is not a member of a nested collection.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @param path The path of the root entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The element value to check for non-membership.
 * @return A [Predicate] that tests whether an element is not a member of the nested collection.
 */
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isNotMember(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate = criteriaBuilder.isNotMember(value, resolve(path))

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
@ExperimentalApi
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
@ExperimentalApi
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
@ExperimentalApi
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
@ExperimentalApi
fun <ROOT, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.arrayNotContains(
    path: Path<ROOT>,
    criteriaBuilder: CriteriaBuilder,
    value: E,
): Predicate =
    (criteriaBuilder as HibernateCriteriaBuilder).run {
        not(arrayContains(resolve(path) as Expression<Array<E>>, value))
    }
