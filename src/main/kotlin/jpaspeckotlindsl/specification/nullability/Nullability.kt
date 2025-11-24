package jpaspeckotlindsl.specification.nullability

import jpaspeckotlindsl.predicate.nullability.isNotNull
import jpaspeckotlindsl.predicate.nullability.isNull
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the property is null.
 *
 * @receiver [T] – the type of the entity.
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
 * @return A [Specification] that checks if the property is not null.
 */
fun <T : Any, P> KProperty1<T, P>.isNotNull(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isNotNull(root, criteriaBuilder)
    }
