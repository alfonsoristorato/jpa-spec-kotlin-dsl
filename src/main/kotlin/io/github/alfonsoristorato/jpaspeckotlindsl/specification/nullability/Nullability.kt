package io.github.alfonsoristorato.jpaspeckotlindsl.specification.nullability

import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.nullability.isNotNull
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.nullability.isNull
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property is null.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @return A [Specification] that checks if the property is null.
 */
fun <T : Any, P> KProperty1<T, P>.isNull(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isNull(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that checks if the property is not null.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @return A [Specification] that checks if the property is not null.
 */
fun <T : Any, P> KProperty1<T, P>.isNotNull(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isNotNull(root, criteriaBuilder)
    }
