# Examples

This document provides comprehensive examples of using the JPA Specification Kotlin DSL.

## Table of Contents

- [Setup](#setup)
- [Basic Operations](#basic-operations)
    - [Equality](#equality)
    - [Comparison](#comparison)
    - [String Operations](#string-operations)
    - [Nullability](#nullability)
    - [Boolean Operations](#boolean-operations)
    - [Inclusion](#inclusion)
    - [Collection Operations](#collection-operations)
- [Combining Specifications](#combining-specifications)
- [Working with Joins](#working-with-joins-experimental)
    - [Using joinWithPredicate](#using-joinwithpredicate)
    - [Using fetchJoinWithPredicate](#using-fetchJoinWithPredicate)
    - [Using joinWithPredicates](#using-joinwithpredicates)
    - [Using fetchJoinWithPredicates](#using-fetchJoinWithPredicates)
    - [Join Types](#join-types)
- [PredicateSpecification vs Specification](#predicatespecification-vs-specification)
- [Real-World Use Cases](#real-world-use-cases)
- [Testing](#testing)
- [Inspiration](#inspiration)

## Setup

Define your JPA entities and repositories as usual:

```kotlin
@Entity
data class User(
    @Id
    val id: Long,
    val name: String,
    val age: Int,
    val email: String?,
    val role: String,
    val isActive: Boolean
)

interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User>
```

## Basic Operations

**Note:** The DSL intelligently handles nullable properties, i.e. you can use `equal(value)` for both nullable and non-nullable properties. 

### Equality

The DSL provides type-safe equality operations for all property types:

```kotlin
// Equal - exact match
val activeUsers = User::isActive.equal(true)
val specificAge = User::age.equal(25)
val adminRole = User::role.equal("ADMIN")

repository.findAll(activeUsers)

// Not equal - exclusion
val notAdmin = User::role.notEqual("ADMIN")
val notInactive = User::isActive.notEqual(false)

repository.findAll(notAdmin)
```

### Comparison

Comparison operations work with any `Comparable` type (numbers, dates, strings, etc.):

```kotlin
// Greater than
val adults = User::age.greaterThan(17)
val seniors = User::age.greaterThanOrEqualTo(65)

// Less than
val youngAdults = User::age.lessThan(30)
val underAge = User::age.lessThanOrEqualTo(17)

// Between
val workingAge = User::age.between(18, 65)

repository.findAll(workingAge)
```

### String Operations

String operations support pattern matching and case transformation:

```kotlin
// Pattern matching with LIKE
val gmailUsers = User::email.like("%@gmail.com")
val startsWithJohn = User::name.like("John%")
val doesNotContainSmith = User::name.notLike("%Smith%")

repository.findAll(gmailUsers)
```

### Nullability

Type-safe null checks for nullable properties:

```kotlin
// Check for null
val usersWithoutEmail = User::email.isNull()
val missingData = User::lastName.isNull()

// Check for not null
val usersWithEmail = User::email.isNotNull()
val completeProfiles = User::lastName.isNotNull()

// Combining with other operations
val activeWithEmail = User::isActive.equal(true) and User::email.isNotNull()

repository.findAll(usersWithEmail)
```

### Boolean Operations

Dedicated operations for boolean properties:

```kotlin
// Check true
val activeUsers = User::isActive.isTrue()
val verifiedAccounts = User::isVerified.isTrue()

// Check false
val inactiveUsers = User::isActive.isFalse()
val unverifiedAccounts = User::isVerified.isFalse()

repository.findAll(activeUsers)
```

### Inclusion

Check if a property's value matches a given value using the IN clause:

```kotlin
// Check if age is in a specific value
val usersAge25 = User::age.`in`(25)
val usersAge30 = User::age.`in`(30)

// Works with any property type
val adminOrManagerRole = User::role.`in`(listOf("ADMIN", "MANAGER"))
val specificEmail = User::email.`in`("john@example.com")
val activeStatus = User::isActive.`in`(true)

repository.findAll(usersAge25)

// Combining with other operations
val activeAdminsOrManagers = User::isActive.`in`(true) and User::role.`in`(listOf("ADMIN", "MANAGER"))

repository.findAll(activeAdminsOrManagers)
```

### Collection Operations

Operations for working with collection properties (e.g., `@ElementCollection` or `@OneToMany`):

```kotlin
// Check if collection is empty
val postsWithoutTags = Post::tags.isEmpty()

// Check if collection is not empty
val postsWithTags = Post::tags.isNotEmpty()

// Check if an element is a member of the collection
val kotlinPosts = Post::tags.isMember("kotlin")
val javaPosts = Post::tags.isMember("java")

// Check if an element is not a member of the collection
val nonKotlinPosts = Post::tags.isNotMember("kotlin")

repository.findAll(kotlinPosts)

// Combining with other operations
val activeKotlinPosts = Post::isPublished.isTrue() and Post::tags.isMember("kotlin")

repository.findAll(activeKotlinPosts)
```

## Combining Specifications

### Using `and`

```kotlin
// Simple AND
val activeAdults = User::isActive.isTrue() and User::age.greaterThanOrEqualTo(18)

// Multiple and conditions
val qualifiedUsers = User::isActive.isTrue() and
        User::age.greaterThanOrEqualTo(18) and
        User::email.isNotNull()

// Multiple and conditions with different operators
val qualifiedUsers = and(
    User::isActive.isTrue(),
    User::age.greaterThanOrEqualTo(18),
    User::email.isNotNull()
)

repository.findAll(activeAdults)
```

### Using `or`

```kotlin
// Simple OR
val youngOrSenior = User::age.lessThan(25) or User::age.greaterThan(65)

// Multiple or conditions
val priorityUsers = User::role.equal("ADMIN") or
        User::role.equal("MODERATOR") or
        User::isPremium.isFalse()

// Multiple or conditions with different operators
val priorityUsers = or(
    User::role.equal("ADMIN"),
    User::role.equal("MODERATOR"),
    User::isPremium.isFalse()
)

repository.findAll(youngOrSenior)
```

## Working with Joins 

The DSL supports joins for querying across entity relationships.
An equivalent of the below functions is available as a fetch-join alternative for eager loading.

### Using joinWithPredicate

The `joinWithPredicate` function provides a clean, concise syntax for joins with a single predicate:

```kotlin
val commentsWithUserNamed = Comment::user.joinWithPredicate { userJoin, cb ->
    User::name.equal(userJoin, cb, "name")
}

val commentsWithPostTitled = Comment::post.joinWithPredicate { postJoin, cb ->
    Post::title.equal(postJoin, cb, "title")
}

val commentsWithUserNamedLike = Comment::user.joinWithPredicate { userJoin, cb ->
    User::name.like(userJoin, cb, "pattern")
}

val commentsWithUserAgedAtLeast = Comment::user.joinWithPredicate { userJoin, cb ->
    User::age.greaterThan(userJoin, cb, 20)
}

repository.findAll(commentsWithUserAgedAtLeast)
```

### Using fetchJoinWithPredicate

The `fetchJoinWithPredicate` function provides a clean, concise syntax for fetch-joins with a single predicate:

```kotlin
val commentsWithUserNamed = Comment::user.fetchJoinWithPredicate { userJoin, cb ->
    User::name.equal(userJoin, cb, "name")
}

val commentsWithPostTitled = Comment::post.fetchJoinWithPredicate { postJoin, cb ->
    Post::title.equal(postJoin, cb, "title")
}

val commentsWithUserNamedLike = Comment::user.fetchJoinWithPredicate { userJoin, cb ->
    User::name.like(userJoin, cb, "pattern")
}

val commentsWithUserAgedAtLeast = Comment::user.fetchJoinWithPredicate { userJoin, cb ->
    User::age.greaterThan(userJoin, cb, 20)
}

repository.findAll(commentsWithUserAgedAtLeast)
```

### Using joinWithPredicates

When you need to combine multiple conditions on the joined entity, use `joinWithPredicates`:

```kotlin
val commentsWithPostTitledAndContentEquals = Comment::post.joinWithPredicates { postJoin, cb ->
    listOf(
        Post::title.equal(postJoin, cb, title),
        Post::content.equal(postJoin, cb, content)
    )
}
val commentsWithUserAgeAtLeastAndUserNameEquals = Comment::user.joinWithPredicates { userJoin, cb ->
    listOf(
        User::age.greaterThanOrEqualTo(userJoin, cb, 10),
        User::name.equals(userJoin, cb, "name")
    )
}

repository.findAll(commentsWithUserAgedAtLeast)
```

**Note:** Multiple predicates are automatically combined with AND logic.

### Using fetchJoinWithPredicates

When you need to combine multiple conditions on the fetch-joined entity, use `fetchJoinWithPredicates`:

```kotlin
val commentsWithPostTitledAndContentEquals = Comment::post.fetchJoinWithPredicates { postJoin, cb ->
    listOf(
        Post::title.equal(postJoin, cb, title),
        Post::content.equal(postJoin, cb, content)
    )
}
val commentsWithUserAgeAtLeastAndUserNameEquals = Comment::user.fetchJoinWithPredicates { userJoin, cb ->
    listOf(
        User::age.greaterThanOrEqualTo(userJoin, cb, 10),
        User::name.equals(userJoin, cb, "name")
    )
}

repository.findAll(commentsWithUserAgedAtLeast)
```

**Note:** Multiple predicates are automatically combined with AND logic.

### Join Types

You can specify different join types (INNER, LEFT, RIGHT).

## Testing

This DSL is thoroughly tested using [Kotest](https://kotest.io/) with real database integration tests. Every feature has
corresponding tests that:

- Use real JPA entities and repositories
- Execute against an actual pg database (test-containers)

Feel free to check
the [tests](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/tree/main/src/test/kotlin/io/github/alfonsoristorato/jpaspeckotlindsl)
for practical examples of usage patterns.



