# Combining Predicates

These `and`/`or` combine raw `Predicate`s, taking the `criteriaBuilder` as their first argument followed by a
`vararg` of `Predicate`s. Reach for them inside any lambda that already gives you a `CriteriaBuilder` and builds
raw `Predicate`s directly, such as a `Specification` or `PredicateSpecification`.

## `and`

```kotlin
val activeAdults =
    Specification<User> { root, _, criteriaBuilder ->
        val isActive = User::isActive.isTrue(root, criteriaBuilder)
        val isAdult = User::age.greaterThanOrEqualTo(root, criteriaBuilder, 18)

        and(criteriaBuilder, isActive, isAdult)
    }
```

## `or`

```kotlin
val priorityUsers =
    PredicateSpecification<User> { root, criteriaBuilder ->
        val isAdmin = User::role.equal(root, criteriaBuilder, "ADMIN")
        val isModerator = User::role.equal(root, criteriaBuilder, "MODERATOR")
        val isPremium = User::isPremium.isTrue(root, criteriaBuilder)

        or(criteriaBuilder, isAdmin, isModerator, isPremium)
    }
```
