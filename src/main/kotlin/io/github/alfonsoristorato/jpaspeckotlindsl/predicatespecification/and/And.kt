package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.and

import org.springframework.data.jpa.domain.PredicateSpecification

/**
 * ANDs the given [PredicateSpecification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other the other [PredicateSpecification].
 * @return The conjunction of the specifications.
 */
infix fun <T : Any> PredicateSpecification<T>.and(other: PredicateSpecification<T>): PredicateSpecification<T> = and(other)
