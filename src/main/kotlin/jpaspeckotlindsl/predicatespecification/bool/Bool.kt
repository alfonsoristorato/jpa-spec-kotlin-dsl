package jpaspeckotlindsl.predicatespecification.bool

import jpaspeckotlindsl.predicate.bool.isFalse
import jpaspeckotlindsl.predicate.bool.isTrue
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that checks if the Boolean property is true.
 *
 * @receiver [T] – the type of the entity.
 * @return A [PredicateSpecification] that checks if the property is true.
 */
fun <T : Any> KProperty1<T, Boolean>.isTrue(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isTrue(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nullable Boolean property is true.
 *
 * @receiver [T] – the type of the entity.
 * @return A [PredicateSpecification] that checks if the property is true.
 */
@JvmName("isTrueNullable")
fun <T : Any> KProperty1<T, Boolean?>.isTrue(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isTrue(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that checks if the Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @return A [PredicateSpecification] that checks if the property is false.
 */
fun <T : Any> KProperty1<T, Boolean>.isFalse(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isFalse(from, criteriaBuilder)
    }

/**
 * Creates a [PredicateSpecification] that checks if the nullable Boolean property is false.
 *
 * @receiver [T] – the type of the entity.
 * @return A [PredicateSpecification] that checks if the property is false.
 */
@JvmName("isFalseNullable")
fun <T : Any> KProperty1<T, Boolean?>.isFalse(): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        isFalse(from, criteriaBuilder)
    }
