package io.github.alfonsoristorato.jpaspeckotlindsl.specification.equality

import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.notEqual
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks for equality between the property and the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param value the value to compare against.
 * @return A [Specification] that checks for equality.
 */
fun <T : Any, P> KProperty1<T, P>.equal(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        equal(root, criteriaBuilder, value)
    }

/**
 * Creates a [Specification] that checks for inequality between the property and the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param value the value to compare against.
 * @return A [Specification] that checks for inequality.
 */
fun <T : Any, P> KProperty1<T, P>.notEqual(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        notEqual(root, criteriaBuilder, value)
    }
