package jpaspeckotlindsl.predicate.equality

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import kotlin.reflect.KProperty1

/**
 * Creates a [Predicate] that checks if the property is equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is equal to the given value.
 */
fun <T, P> KProperty1<T, P>.equal(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.equal(path.get<P>(this.name), value)

/**
 * Creates a [Predicate] that checks if the property is not equal to the given value.
 *
 * @receiver [T] – the type of the entity.
 * @receiver [P] - the type of the property.
 * @param path The path of the entity.
 * @param criteriaBuilder The criteria builder.
 * @param value The value to compare against.
 * @return A [Predicate] that checks if the property is not equal to the given value.
 */
fun <T, P> KProperty1<T, P>.notEqual(
    path: Path<T>,
    criteriaBuilder: CriteriaBuilder,
    value: P,
): Predicate = criteriaBuilder.notEqual(path.get<P>(this.name), value)
