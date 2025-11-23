package jpaspeckotlindsl.specification.bool

import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that checks if the Boolean property is true.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is true.
 */
fun <T : Any> KProperty1<T, Boolean>.isTrue(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        criteriaBuilder.isTrue(
            root.get(this.name),
        )
    }

/**
 * Creates a [Specification] that checks if the nullable Boolean property is true.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is true.
 */
@JvmName("isTrueNullable")
fun <T : Any> KProperty1<T, Boolean?>.isTrue(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        criteriaBuilder.isTrue(
            root.get(this.name),
        )
    }

/**
 * Creates a [Specification] that checks if the Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is false.
 */
fun <T : Any> KProperty1<T, Boolean>.isFalse(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        criteriaBuilder.isFalse(
            root.get(this.name),
        )
    }

/**
 * Creates a [Specification] that checks if the nullable Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is false.
 */
@JvmName("isFalseNullable")
fun <T : Any> KProperty1<T, Boolean?>.isFalse(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        criteriaBuilder.isFalse(
            root.get(this.name),
        )
    }
