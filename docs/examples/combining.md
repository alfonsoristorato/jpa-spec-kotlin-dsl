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

## Mixing `and` and `or`

Wrap conditions in parentheses to control precedence:

```kotlin
// Active users who are either admins or adults
val spec = User::isActive.isTrue() and
        (User::role.equal("ADMIN") or User::age.greaterThanOrEqualTo(18))

// Users who are either premium, or active adults with an email
val spec = User::isPremium.isTrue() or
        (User::isActive.isTrue() and User::age.greaterThanOrEqualTo(18) and User::email.isNotNull())
```

The top-level functions also compose naturally:

```kotlin
val adminsOrAdults = or(
    User::role.equal("ADMIN"),
    User::age.greaterThanOrEqualTo(18),
)

val activeAdminsOrAdults = User::isActive.isTrue() and adminsOrAdults
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
