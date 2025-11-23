package jpaspeckotlindsl.predicatespecification.or

import org.springframework.data.jpa.domain.PredicateSpecification

/**
 * ORs the given [PredicateSpecification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other – the other [PredicateSpecification].
 * @return The disjunction of the specifications.
 */
infix fun <T : Any> PredicateSpecification<T>.or(other: PredicateSpecification<T>): PredicateSpecification<T> = this.or(other)
