package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.join

import io.github.alfonsoristorato.jpaspeckotlindsl.join.join
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.From
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.PredicateSpecification
import kotlin.reflect.KProperty1

/**
 * Creates a [PredicateSpecification] that performs a join and applies a list of [Predicate]s by ANDing them to the joined entity.
 *
 * @receiver [T] – the type of the root entity.
 * @receiver [R] – the type of the joined entity.
 * @param joinType The type of join to perform (defaults to [JoinType.INNER]).
 * @param predicateBuilder A lambda that receives the joined entity path and criteria builder, and returns a predicate.
 * @return A [PredicateSpecification] that performs the join and applies the predicates.
 */

fun <T : Any, R> KProperty1<T, R>.joinWithPredicates(
    joinType: JoinType = JoinType.INNER,
    predicateBuilder: (From<T, R>, CriteriaBuilder) -> List<Predicate>,
): PredicateSpecification<T> =
    PredicateSpecification { from, criteriaBuilder ->
        val joinedPath = join(from, joinType)
        criteriaBuilder.and(
            *predicateBuilder(joinedPath, criteriaBuilder).toTypedArray(),
        )
    }

/**
 * Creates a [PredicateSpecification] that performs a join and applies a [Predicate] to the joined entity.
 *
 * @receiver [T] – the type of the root entity.
 * @receiver [R] – the type of the joined entity.
 * @param joinType The type of join to perform (defaults to [JoinType.INNER]).
 * @param predicateBuilder A lambda that receives the joined entity path and criteria builder, and returns a predicate.
 * @return A [PredicateSpecification] that performs the join and applies the predicate.
 */

fun <T : Any, R> KProperty1<T, R>.joinWithPredicate(
    joinType: JoinType = JoinType.INNER,
    predicateBuilder: (From<T, R>, CriteriaBuilder) -> Predicate,
): PredicateSpecification<T> =
    joinWithPredicates(joinType) { joinedPath, criteriaBuilder ->
        listOf(predicateBuilder(joinedPath, criteriaBuilder))
    }
