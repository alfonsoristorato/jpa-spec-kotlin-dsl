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
- [Combining Specifications](#combining-specifications)
- [Working with Joins](#working-with-joins-experimental)
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

**Note:** The DSL intelligently handles nullable properties - you can use `equal(value)` for both nullable and
non-nullable properties.

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

## Combining Specifications

### Using `and`

```kotlin
// Simple AND
val activeAdults = User::isActive.isTrue() and User::age.greaterThanOrEqualTo(18)

// Multiple conditions
val qualifiedUsers = User::isActive.isTrue() and
        User::age.greaterThanOrEqualTo(18) and
        User::email.isNotNull()

repository.findAll(activeAdults)
```

### Using `or`

```kotlin
// Simple OR
val youngOrSenior = User::age.lessThan(25) or User::age.greaterThan(65)

// Multiple alternatives
val priorityUsers = User::role.equal("ADMIN") or
        User::role.equal("MODERATOR") or
        User::isPremium.isFalse()

repository.findAll(youngOrSenior)
```

## Working with Joins (Experimental)

The DSL supports joins for querying across entity relationships. This feature is marked as experimental as the API may
evolve.

**Important:** Join operations must be used within a `Specification {}` or `PredicateSpecification {}` lambda block, as they require access to the JPA `Root` and `CriteriaBuilder`.

### Setup

First, define your related entities:

```kotlin
@Entity
data class Post(
    @Id val id: Long,
    val title: String,
    @ManyToOne val author: User
)

@Entity
data class Comment(
    @Id val id: Long,
    val content: String,
    @ManyToOne val post: Post,
    @ManyToOne val author: User
)

interface PostRepository : JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>
interface CommentRepository : JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>
```

### Using Joins with Specification

```kotlin
@OptIn(ExperimentalJoinApi::class)
fun findCommentsByPostTitle(title: String): List<Comment> {
    val spec = Specification { root, _, criteriaBuilder ->
        // Create the join from Comment to Post
        val postJoin = Comment::post.join(root)
        
        // Use the join to filter by post title
        Post::title.equal(postJoin, criteriaBuilder, title)
    }
    
    return commentRepository.findAll(spec)
}

@OptIn(ExperimentalJoinApi::class)
fun findCommentsByAuthorName(authorName: String): List<Comment> {
    val spec = Specification { root, _, criteriaBuilder ->
        // Create the join from Comment to User (author)
        val authorJoin = Comment::author.join(root)
        
        // Use the join to filter by author name
        User::name.equal(authorJoin, criteriaBuilder, authorName)
    }
    
    return commentRepository.findAll(spec)
}
```

### Join Types

You can specify different join types (INNER, LEFT, RIGHT):


## Testing

This DSL is thoroughly tested using [Kotest](https://kotest.io/) with real database integration tests. Every feature has
corresponding tests that:

- Use real JPA entities and repositories
- Execute against an actual pg database (test-containers)

Feel free to check
the [tests](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/tree/main/src/test/kotlin/io/github/alfonsoristorato/jpaspeckotlindsl)
for practical examples of usage patterns.



