package io.github.alfonsoristorato.jpaspeckotlindsl.join

import io.github.alfonsoristorato.jpaspeckotlindsl.internal.ExperimentalJoinApi
import jakarta.persistence.criteria.From
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.JoinType
import kotlin.reflect.KProperty1

/**
 * Creates a [Join] for this property on the given [From].
 *
 * @receiver [Z] – the type of the root entity.
 * @receiver [T] – the type of the current entity.
 * @receiver [R] – the type of the joined property.
 * @param from The JPA [From] to join from.
 * @param joinType The type of join to perform (defaults to [JoinType.INNER]).
 * @return A [Join] representing the join.
 */
@ExperimentalJoinApi
fun <Z, T, R> KProperty1<T, R>.join(
    from: From<Z, T>,
    joinType: JoinType = JoinType.INNER,
): Join<T, R> = from.join(this.name, joinType)
