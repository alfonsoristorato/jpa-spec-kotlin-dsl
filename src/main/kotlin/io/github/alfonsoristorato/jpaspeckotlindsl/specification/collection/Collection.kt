package io.github.alfonsoristorato.jpaspeckotlindsl.specification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isEmpty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isMember
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isNotEmpty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isNotMember
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that tests whether a collection is empty.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @return A [Specification] that tests whether a collection is empty.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isEmpty(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isEmpty(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that tests whether a collection is not empty.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @return A [Specification] that tests whether a collection is not empty.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isNotEmpty(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isNotEmpty(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that tests whether an element is a member of a collection.
 * If the collection is empty, the predicate will be false.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param value – the element value to check for membership.
 * @return A [Specification] that tests whether an element is a member of a collection.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isMember(value: E): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isMember(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that tests whether an element is not a member of a collection.
 * If the collection is empty, the predicate will be true.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param value – the element value to check for non-membership.
 * @return A [Specification] that tests whether an element is not a member of a collection.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isNotMember(value: E): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isNotMember(root, criteriaBuilder, value)
    }
