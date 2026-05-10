# Joins

The DSL supports joins for querying across entity relationships. Every join function has a fetch-join equivalent for eager loading.

## Setup

```kotlin
@Entity
class Comment(
    @Id val id: Long,
    val content: String,
    @ManyToOne val user: User,
    @ManyToOne val post: Post,
)

@Entity
class Persona(
    @Id val id: Long,
    val name: String,
    @OneToOne val organisation: Organisation?,
)
```

## Single predicate: `joinWithPredicate` / `fetchJoinWithPredicate`

```kotlin
val commentsFromAlice = Comment::user.joinWithPredicate { userJoin, cb ->
    User::name.equal(userJoin, cb, "Alice")
}

// Eager load the joined entity at the same time
val commentsFromAliceEager = Comment::user.fetchJoinWithPredicate { userJoin, cb ->
    User::name.equal(userJoin, cb, "Alice")
}
```

## Multiple predicates: `joinWithPredicates` / `fetchJoinWithPredicates`

Multiple predicates are automatically combined with AND logic.

```kotlin
val commentsFromAliceOnTitledPost = Comment::post.joinWithPredicates { postJoin, cb ->
    listOf(
        Post::title.equal(postJoin, cb, "Hello World"),
        Post::content.equal(postJoin, cb, "Some content"),
    )
}
```

## Nullable relationships

Use the `Nullable` variants when the relationship property is nullable (e.g. `Organisation?`).
This avoids a type mismatch - the lambda receives the unwrapped non-nullable type.

```kotlin
// Persona::organisation is Organisation? - use joinNullableWithPredicate
val personasInAcme = Persona::organisation.joinNullableWithPredicate { orgJoin, cb ->
    Organisation::name.equal(orgJoin, cb, "Acme Corp")
}

// Multiple predicates on a nullable relationship
val personasInAcmeUS = Persona::organisation.joinNullableWithPredicates { orgJoin, cb ->
    listOf(
        Organisation::name.equal(orgJoin, cb, "Acme Corp"),
        Organisation::country.equal(orgJoin, cb, "US"),
    )
}

// Fetch variants available too
val personasInAcmeEager = Persona::organisation.fetchJoinNullableWithPredicate { orgJoin, cb ->
    Organisation::name.equal(orgJoin, cb, "Acme Corp")
}
```

## Join types

All join functions accept an optional `JoinType` parameter (defaults to `INNER`):

```kotlin
val commentsOrNoUser = Comment::user.joinWithPredicate(JoinType.LEFT) { userJoin, cb ->
    User::name.equal(userJoin, cb, "Alice")
}
```
