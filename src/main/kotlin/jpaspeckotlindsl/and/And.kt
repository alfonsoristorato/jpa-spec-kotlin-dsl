package jpaspeckotlindsl.and

import org.springframework.data.jpa.domain.Specification

/**
 * ANDs the given Specification to the current one.
 *
 * @param other – the other Specification.
 * @return The conjunction of the specifications.
 */
infix fun <T : Any> Specification<T>.and(other: Specification<T>): Specification<T> = this.and(other)
