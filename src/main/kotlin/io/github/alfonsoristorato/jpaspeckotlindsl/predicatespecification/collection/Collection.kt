package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isEmpty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isMember
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isNotEmpty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.collection.isNotMember
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that tests whether a collection is empty.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @return A [PredicateSpecification] that tests whether a collection is empty.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isEmpty(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isEmpty(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a collection is not empty.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @return A [PredicateSpecification] that tests whether a collection is not empty.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isNotEmpty(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isNotEmpty(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that tests whether an element is a member of a collection.
 * If the collection is empty, the predicate will be false.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param value – the element value to check for membership.
 * @return A [PredicateSpecification] that tests whether an element is a member of a collection.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isMember(value: E): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isMember(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether an element is not a member of a collection.
 * If the collection is empty, the predicate will be true.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [P] - the type of the [Collection] property.
 * @param value – the element value to check for non-membership.
 * @return A [PredicateSpecification] that tests whether an element is not a member of a collection.
 */
fun <T : Any, E, P : Collection<E>> KProperty1<T, P>.isNotMember(value: E): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isNotMember(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested collection is empty.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @return A [PredicateSpecification] that tests whether the nested collection is empty.
 */
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isEmpty(): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        isEmpty(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that tests whether a nested collection is not empty.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @return A [PredicateSpecification] that tests whether the nested collection is not empty.
 */
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isNotEmpty(): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        isNotEmpty(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that tests whether an element is a member of a nested collection.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @param value – the element value to check for membership.
 * @return A [PredicateSpecification] that tests whether an element is a member of the nested collection.
 */
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isMember(value: E): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        isMember(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that tests whether an element is not a member of a nested collection.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [E] - the type of the element in the [Collection].
 * @receiver [PROP] - the type of the [Collection] property.
 * @param value – the element value to check for non-membership.
 * @return A [PredicateSpecification] that tests whether an element is not a member of the nested collection.
 */
fun <ROOT : Any, E, PROP : Collection<E>> NestedProperty<ROOT, PROP>.isNotMember(value: E): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        isNotMember(from, criteriaBuilder, value)
    }
