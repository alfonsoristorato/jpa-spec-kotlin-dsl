package jpaspeckotlindsl.predicatespecification.nullability

import jpaspeckotlindsl.predicate.nullability.isNotNull
import jpaspeckotlindsl.predicate.nullability.isNull
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the property is null.
 *
 * @receiver [T] – the type of the entity.
 * @return A [PredicateSpecification] that checks if the property is null.
 */
fun <T : Any, P> KProperty1<T, P>.isNull(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder -> this.isNull(from, criteriaBuilder) }

/**
 * Creates a [PredicateSpecification] that checks if the property is not null.
 *
 * @receiver [T] – the type of the entity.
 * @return A [PredicateSpecification] that checks if the property is not null.
 */
fun <T : Any, P> KProperty1<T, P>.isNotNull(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder -> this.isNotNull(from, criteriaBuilder) }
