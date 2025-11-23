package jpaspeckotlindsl.predicatespecification.and

import org.springframework.data.jpa.domain.PredicateSpecification

/**
 * ANDs the given [PredicateSpecification] to the current one.
 *
 * @param other – the other [PredicateSpecification].
 * @return The conjunction of the specifications.
 */
infix fun <T : Any> PredicateSpecification<T>.and(other: PredicateSpecification<T>): PredicateSpecification<T> = this.and(other)
