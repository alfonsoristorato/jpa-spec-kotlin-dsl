# Joins

The DSL supports joins for querying across entity relationships. Every join function has a fetch-join equivalent for eager loading.

## Setup

```kotlin
@Entity
class Comment(
    @ManyToOne val post: Post,
    @OneToOne val persona: Persona,
    val content: String,
)

@Entity
class Post(
    @ManyToOne val persona: Persona,
    val title: String,
    val content: String,
)

@Entity
class Persona(
    val name: String,
    @OneToOne val organisation: Organisation?,
)
```

## Single predicate: `joinWithPredicate` / `fetchJoinWithPredicate`

```kotlin
val commentsFromAlice = Comment::persona.joinWithPredicate { personaJoin, cb ->
    Persona::name.equal(personaJoin, cb, "Alice")
}

val commentsOnPost = Comment::post.joinWithPredicate { postJoin, cb ->
    Post::title.equal(postJoin, cb, "Hello World")
}

// Eager load the joined entity at the same time
val commentsFromAliceEager = Comment::persona.fetchJoinWithPredicate { personaJoin, cb ->
    Persona::name.equal(personaJoin, cb, "Alice")
}
```

## Multiple predicates: `joinWithPredicates` / `fetchJoinWithPredicates`

Multiple predicates are automatically combined with AND logic.

```kotlin
val commentsOnMatchingPost = Comment::post.joinWithPredicates { postJoin, cb ->
    listOf(
        Post::title.equal(postJoin, cb, "Hello World"),
        Post::content.equal(postJoin, cb, "Some content"),
    )
}

val postsByAlice = Post::persona.joinWithPredicates { personaJoin, cb ->
    listOf(
        Persona::name.equal(personaJoin, cb, "Alice"),
        Persona::age.greaterThan(personaJoin, cb, 18),
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
val personasInAcmeUK = Persona::organisation.joinNullableWithPredicates { orgJoin, cb ->
    listOf(
        Organisation::name.equal(orgJoin, cb, "Acme Corp"),
        Organisation::country.equal(orgJoin, cb, "UK"),
    )
}

// Fetch variant
val personasInAcmeEager = Persona::organisation.fetchJoinNullableWithPredicate { orgJoin, cb ->
    Organisation::name.equal(orgJoin, cb, "Acme Corp")
}
```

## Join types

All join functions accept an optional `JoinType` parameter (defaults to `INNER`):

```kotlin
val commentsWithOrWithoutPost = Comment::post.joinWithPredicate(JoinType.LEFT) { postJoin, cb ->
    Post::title.equal(postJoin, cb, "Hello World")
}
```
