package io.github.alfonsoristorato.jpaspeckotlindsl.specification.combiner

import org.springframework.data.jpa.domain.Specification

/**
 * ANDs the given [Specification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other – the other [Specification].
 * @return The conjunction of the specifications.
 */
infix fun <T : Any> Specification<T>.and(other: Specification<T>): Specification<T> = and(other)

/**
 * ANDs all the given [Specification]s together.
 *
 * @param T – the type of the entity.
 * @param predicateSpecification – the [Specification]s to combine.
 * @return The conjunction of all specifications.
 */
fun <T : Any> and(vararg predicateSpecification: Specification<T>): Specification<T> = predicateSpecification.reduce(Specification<T>::and)

/**
 * ORs the given [Specification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other – the other [Specification].
 * @return The disjunction of the specifications.
 */
infix fun <T : Any> Specification<T>.or(other: Specification<T>): Specification<T> = or(other)

/**
 * ORs all the given [Specification]s together.
 *
 * @param T – the type of the entity.
 * @param specification – the [Specification]s to combine.
 * @return The disjunction of all specifications.
 */
fun <T : Any> or(vararg specification: Specification<T>): Specification<T> = specification.reduce(Specification<T>::or)
