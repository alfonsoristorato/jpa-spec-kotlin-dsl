package jpaspeckotlindsl.or

import org.springframework.data.jpa.domain.Specification

/**
 * ORs the given specification to the current one.
 *
 * @param other – the other Specification.
 * @return The disjunction of the specification.
 */
infix fun <T : Any> Specification<T>.or(other: Specification<T>): Specification<T> = this.or(other)
