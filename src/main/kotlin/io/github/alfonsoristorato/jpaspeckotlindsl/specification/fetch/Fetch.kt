package io.github.alfonsoristorato.jpaspeckotlindsl.specification.fetch

import io.github.alfonsoristorato.jpaspeckotlindsl.fetch.fetch
import io.github.alfonsoristorato.jpaspeckotlindsl.join.join
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.From
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KProperty1

/**
 * Creates a [Specification] that performs a fetch and applies a list of [Predicate]s by ANDing them to the fetch-joined entity.
 *
 * @receiver [T] – the type of the root entity.
 * @receiver [R] – the type of the fetch-joined entity.
 * @param joinType The type of join to perform (defaults to [JoinType.INNER]).
 * @param predicateBuilder A lambda that receives the fetch-joined entity path and criteria builder, and returns a predicate.
 * @return A [Specification] that performs the fetch-join and applies the predicates.
 */

fun <T : Any, R> KProperty1<T, R>.fetchJoinWithPredicates(
    joinType: JoinType = JoinType.INNER,
    predicateBuilder: (From<T, R>, CriteriaBuilder) -> List<Predicate>,
): Specification<T> =
    Specification { root, _, criteriaBuilder ->
        fetch(root, joinType)
        val joinedPath = join(root, joinType)
        criteriaBuilder.and(
            *predicateBuilder(joinedPath, criteriaBuilder).toTypedArray(),
        )
    }

/**
 * Creates a [Specification] that performs a fetch-join and applies a [Predicate] to the fetch-joined entity.
 *
 * @receiver [T] – the type of the root entity.
 * @receiver [R] – the type of the fetch-joined entity.
 * @param joinType The type of join to perform (defaults to [JoinType.INNER]).
 * @param predicateBuilder A lambda that receives the fetch-joined entity path and criteria builder, and returns a predicate.
 * @return A [Specification] that performs the fetch-join and applies the predicate.
 */

fun <T : Any, R> KProperty1<T, R>.fetchJoinWithPredicate(
    joinType: JoinType = JoinType.INNER,
    predicateBuilder: (From<T, R>, CriteriaBuilder) -> Predicate,
): Specification<T> =
    fetchJoinWithPredicates(joinType) { joinedPath, criteriaBuilder ->
        listOf(predicateBuilder(joinedPath, criteriaBuilder))
    }
