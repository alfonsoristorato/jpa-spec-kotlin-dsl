package jpaspeckotlindsl.specification.equality

import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks for equality between the property and the given value.
 *
 * @receiver T – the type of the entity.
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

/**
 * Creates a [Specification] that checks for inequality between the property and the given value.
 *
 * @receiver T – the type of the entity.
 * @param P – the type of the property.
 * @param value – the value to compare against.
 * @return A [Specification] that checks for inequality.
 */
fun <T : Any, P> KProperty1<T, P>.notEqual(value: P): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        criteriaBuilder.notEqual(
            root.get<P>(this.name),
            value,
        )
    }
