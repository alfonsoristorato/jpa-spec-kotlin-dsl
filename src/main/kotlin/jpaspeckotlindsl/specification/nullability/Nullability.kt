package jpaspeckotlindsl.specification.nullability

import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property is null.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is null.
 */
fun <T : Any, P> KProperty1<T, P>.isNull(): Specification<T> =
    Specification { from, _, criteriaBuilder ->
        criteriaBuilder.isNull(
            from.get<P>(this.name),
        )
    }

/**
 * Creates a [Specification] that checks if the property is not null.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is not null.
 */
fun <T : Any, P> KProperty1<T, P>.isNotNull(): Specification<T> =
    Specification { from, _, criteriaBuilder ->
        criteriaBuilder.isNotNull(
            from.get<P>(this.name),
        )
    }
