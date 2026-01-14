package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
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
