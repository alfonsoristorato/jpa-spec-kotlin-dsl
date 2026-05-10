package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.equality

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.notEqual
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks for equality between the property and the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks for equality.
 */
fun <T : Any, P> KProperty1<T, P>.equal(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        equal(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks for inequality between the property and the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks for inequality.
 */
fun <T : Any, P> KProperty1<T, P>.notEqual(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        notEqual(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks for equality on a nested property.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks for equality.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.equal(value: PROP): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        equal(from, criteriaBuilder, value)
    }

/**
 * Creates a [PredicateSpecification] that checks for inequality on a nested property.
 *
 * @receiver [ROOT] – the root entity type.
 * @receiver [PROP] - the type of the property.
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks for inequality.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.notEqual(value: PROP): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        notEqual(from, criteriaBuilder, value)
    }
