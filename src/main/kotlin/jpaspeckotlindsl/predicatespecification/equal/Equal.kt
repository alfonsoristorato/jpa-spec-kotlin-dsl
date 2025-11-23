package jpaspeckotlindsl.predicatespecification.equal

import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks for equality between the property and the given value.
 *
 * @receiver T – the type of the entity.
 * @param P – the type of the property.
 * @param value – the value to compare against.
 * @return A [PredicateSpecification] that checks for equality.
 */
fun <T : Any, P> KProperty1<T, P>.equal(value: P): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        criteriaBuilder.equal(
            from.get<P>(this.name),
            value,
        )
    }
