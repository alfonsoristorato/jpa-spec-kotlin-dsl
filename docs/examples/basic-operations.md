# Basic Operations

All examples below use `Specification` DSL. The same functions are available for `PredicateSpecification` and `Predicate`.

## Setup

```kotlin
@Entity
class User(
    @Id val id: Long,
    val name: String,
    val age: Int,
    val email: String?,
    val role: String,
    val isActive: Boolean,
    val isPremium: Boolean,
)
```

## Equality

```kotlin
val activeUsers = User::isActive.equal(true)
val specificAge = User::age.equal(25)
val adminRole = User::role.equal("ADMIN")

val notAdmin = User::role.notEqual("ADMIN")
val notInactive = User::isActive.notEqual(false)
```

## Comparison

Works with any `Comparable` type - numbers, dates, strings:

```kotlin
val adults = User::age.greaterThan(17)
val seniors = User::age.greaterThanOrEqualTo(65)

val youngAdults = User::age.lessThan(30)
val underAge = User::age.lessThanOrEqualTo(17)

val workingAge = User::age.between(18, 65)
```

## String operations

```kotlin
val gmailUsers = User::email.like("%@gmail.com")
val startsWithJohn = User::name.like("John%")
val doesNotContainSmith = User::name.notLike("%Smith%")
```

## Nullability

```kotlin
val usersWithoutEmail = User::email.isNull()
val usersWithEmail = User::email.isNotNull()
```

## Boolean operations

```kotlin
val activeUsers = User::isActive.isTrue()
val inactiveUsers = User::isActive.isFalse()
```

## Inclusion

```kotlin
// Single value
val age25 = User::age.`in`(25)

// Multiple values via list
val adminOrManager = User::role.`in`(listOf("ADMIN", "MANAGER"))

// `containedIn` is an alias for `in` that avoids the backtick-escaped keyword
val admins = User::role.containedIn("ADMIN")

// Negation (NOT IN) - `notIn` and its `notContainedIn` alias are equivalent
val notAdminOrManager = User::role.notIn(listOf("ADMIN", "MANAGER"))
val stillNotAdminOrManager = User::role.notContainedIn(listOf("ADMIN", "MANAGER"))
```
