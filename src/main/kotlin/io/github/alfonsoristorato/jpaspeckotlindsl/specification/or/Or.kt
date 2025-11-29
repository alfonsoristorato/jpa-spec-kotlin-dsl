package io.github.alfonsoristorato.jpaspeckotlindsl.specification.or

import org.springframework.data.jpa.domain.Specification

/**
 * ORs the given [Specification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other – the other [Specification].
 * @return The disjunction of the specifications.
 */
infix fun <T : Any> Specification<T>.or(other: Specification<T>): Specification<T> = or(other)
