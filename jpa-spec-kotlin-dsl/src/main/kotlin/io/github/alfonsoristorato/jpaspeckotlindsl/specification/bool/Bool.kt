package io.github.alfonsoristorato.jpaspeckotlindsl.specification.bool

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.bool.isFalse
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.bool.isTrue
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
        isTrue(root, criteriaBuilder)
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
        isTrue(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that checks if the Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @return A [Specification] that checks if the property is false.
 */
fun <T : Any> KProperty1<T, Boolean>.isFalse(): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        isFalse(root, criteriaBuilder)
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
        isFalse(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that checks if the nested Boolean property is true.
 *
 * @receiver [ROOT] – the root entity type.
 * @return A [Specification] that checks if the nested property is true.
 */
fun <ROOT : Any> NestedProperty<ROOT, Boolean>.isTrue(): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        isTrue(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that checks if the nested nullable Boolean property is true.
 *
 * @receiver [ROOT] – the root entity type.
 * @return A [Specification] that checks if the nested property is true.
 */
@JvmName("nestedIsTrueNullable")
fun <ROOT : Any> NestedProperty<ROOT, Boolean?>.isTrue(): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        isTrue(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that checks if the nested Boolean property is false.
 *
 * @receiver [ROOT] – the root entity type.
 * @return A [Specification] that checks if the nested property is false.
 */
fun <ROOT : Any> NestedProperty<ROOT, Boolean>.isFalse(): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        isFalse(root, criteriaBuilder)
    }

/**
 * Creates a [Specification] that checks if the nested nullable Boolean property is false.
 *
 * @receiver [ROOT] – the root entity type.
 * @return A [Specification] that checks if the nested property is false.
 */
@JvmName("nestedIsFalseNullable")
fun <ROOT : Any> NestedProperty<ROOT, Boolean?>.isFalse(): Specification<ROOT> =
    Specification { root, _, criteriaBuilder ->
        isFalse(root, criteriaBuilder)
    }
