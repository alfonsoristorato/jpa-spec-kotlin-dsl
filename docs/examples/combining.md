# Combining Specifications

## `and`

```kotlin
// Infix
val activeAdults = User::isActive.isTrue() and User::age.greaterThanOrEqualTo(18)

// Chained
val qualifiedUsers = User::isActive.isTrue() and
        User::age.greaterThanOrEqualTo(18) and
        User::email.isNotNull()
```

## `or`

```kotlin
// Infix
val youngOrSenior = User::age.lessThan(25) or User::age.greaterThan(65)

// Chained
val priorityUsers = User::role.equal("ADMIN") or
        User::role.equal("MODERATOR") or
        User::isPremium.isFalse()
```

## Multi-condition `and` / `or` functions

For more than two conditions, the top-level `and()` and `or()` functions avoid deep nesting:

```kotlin
val qualifiedUsers = and(
    User::isActive.isTrue(),
    User::age.greaterThanOrEqualTo(18),
    User::email.isNotNull(),
)

val priorityUsers = or(
    User::role.equal("ADMIN"),
    User::role.equal("MODERATOR"),
    User::isPremium.isFalse(),
)
```

## Reusable specs

Because specifications are just values, you can compose them freely:

```kotlin
fun activeUsers() = User::isActive.isTrue()
fun adults() = User::age.greaterThanOrEqualTo(18)
fun hasEmail() = User::email.isNotNull()

fun eligibleUsers() = activeUsers() and adults() and hasEmail()

repository.findAll(eligibleUsers())
```
