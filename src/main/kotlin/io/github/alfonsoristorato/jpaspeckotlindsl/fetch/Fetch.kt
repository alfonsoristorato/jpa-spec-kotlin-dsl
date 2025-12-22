package io.github.alfonsoristorato.jpaspeckotlindsl.fetch

import io.github.alfonsoristorato.jpaspeckotlindsl.internal.ExperimentalJoinApi
import jakarta.persistence.criteria.Fetch
import jakarta.persistence.criteria.From
import jakarta.persistence.criteria.JoinType
import kotlin.reflect.KProperty1

/**
 * Creates a [Fetch] for this property on the given [From].
 *
 * @receiver [Z] – the type of the root entity.
 * @receiver [T] – the type of the current entity.
 * @receiver [R] – the type of the fetched property.
 * @param from The JPA [From] to fetch from.
 * @param joinType The type of join to perform (defaults to [JoinType.INNER]).
 * @return A [Fetch] representing the fetch.
 */
@ExperimentalJoinApi
fun <Z, T, R> KProperty1<T, R>.fetch(
    from: From<Z, T>,
    joinType: JoinType = JoinType.INNER,
): Fetch<T, R> = from.fetch(this.name, joinType)
