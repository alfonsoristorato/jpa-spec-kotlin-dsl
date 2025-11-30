package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.combiner

import org.springframework.data.jpa.domain.PredicateSpecification

/**
 * ANDs the given [PredicateSpecification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other the other [PredicateSpecification].
 * @return The conjunction of the specifications.
 */
infix fun <T : Any> PredicateSpecification<T>.and(other: PredicateSpecification<T>): PredicateSpecification<T> = and(other)

/**
 * ANDs all the given [PredicateSpecification]s together.
 *
 * @param T – the type of the entity.
 * @param predicateSpecification – the [PredicateSpecification]s to combine.
 * @return The conjunction of all specifications.
 */
fun <T : Any> and(vararg predicateSpecification: PredicateSpecification<T>): PredicateSpecification<T> =
    predicateSpecification.reduce(PredicateSpecification<T>::and)

/**
 * ORs the given [PredicateSpecification] to the current one.
 *
 * @receiver [T] – the type of the entity.
 * @param other – the other [PredicateSpecification].
 * @return The disjunction of the specifications.
 */
infix fun <T : Any> PredicateSpecification<T>.or(other: PredicateSpecification<T>): PredicateSpecification<T> = or(other)

/**
 * ORs all the given [PredicateSpecification]s together.
 *
 * @param T – the type of the entity.
 * @param predicateSpecification – the [PredicateSpecification]s to combine.
 * @return The disjunction of all specifications.
 */
fun <T : Any> or(vararg predicateSpecification: PredicateSpecification<T>): PredicateSpecification<T> =
    predicateSpecification.reduce(PredicateSpecification<T>::or)
