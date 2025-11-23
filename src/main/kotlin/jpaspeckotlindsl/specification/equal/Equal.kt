package jpaspeckotlindsl.specification.equal

import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks for equality between the property and the given value.
 *
 * @param T – the type of the entity.
 * @param P – the type of the property.
 * @param value – the value to compare against.
 * @return A [Specification] that checks for equality.
 */
fun <T : Any, P> KProperty1<T, P>.equal(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        criteriaBuilder.equal(
            root.get<P>(this.name),
            value,
        )
    }
