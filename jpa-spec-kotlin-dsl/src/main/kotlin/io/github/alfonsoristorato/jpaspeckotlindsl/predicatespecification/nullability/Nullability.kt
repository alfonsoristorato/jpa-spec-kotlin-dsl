package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.nullability

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.nullability.isNotNull
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.nullability.isNull
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the property is null.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @return A [PredicateSpecification] that checks if the property is null.
 */
fun <T : Any, P> KProperty1<T, P>.isNull(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isNull(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that checks if the property is not null.
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @return A [PredicateSpecification] that checks if the property is not null.
 */
fun <T : Any, P> KProperty1<T, P>.isNotNull(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isNotNull(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested property is null.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @return A [PredicateSpecification] that checks if the nested property is null.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.isNull(): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        isNull(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nested property is not null.
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @return A [PredicateSpecification] that checks if the nested property is not null.
 */
fun <ROOT : Any, PROP> NestedProperty<ROOT, PROP>.isNotNull(): PredicateSpecification<ROOT> =
    PredicateSpecification { from, criteriaBuilder ->
        isNotNull(from, criteriaBuilder)
    }
