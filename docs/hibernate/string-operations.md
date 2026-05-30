# String matching

These functions perform pattern matching on string properties using Hibernate's `HibernateCriteriaBuilder`.

!!! note "No opt-in required"
    These functions are stable and do not require `@OptIn(ExperimentalHibernateApi::class)`.

## Setup

```kotlin
@Entity
class User(
    @Id val id: Long,
    val name: String,
    val email: String?,
)
```

## `ilike` / `notIlike`

Case-insensitive `LIKE` pattern matching. Patterns use SQL wildcards: `%` matches any sequence of characters, `_` matches any single character.

```kotlin
val smiths = User::name.ilike("%smith%")
val notSmiths = User::name.notIlike("%smith%")

repository.findAll(smiths)
```

## `likeRegexp` / `notLikeRegexp`

Case-sensitive POSIX regex matching. Uses the PostgreSQL `~` operator under the hood.

```kotlin
val smiths = User::name.likeRegexp("^Smith\\b")
val notSmiths = User::name.notLikeRegexp("^Smith\\b")

repository.findAll(smiths)
```

Unlike `ilike`, the pattern `.*smith.*` would **not** match `"John Smith"` because the match is case-sensitive.

## `ilikeRegexp` / `notIlikeRegexp`

Case-insensitive POSIX regex matching. Uses the PostgreSQL `~*` operator under the hood.

```kotlin
val smiths = User::name.ilikeRegexp(".*smith.*")
val notSmiths = User::name.notIlikeRegexp(".*smith.*")

repository.findAll(smiths)
```

## Nested properties

All functions work on nested properties via the `/` operator:

```kotlin
val spec = (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street).ilike("main%")
val specRegex = (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street).ilikeRegexp("^main")

repository.findAll(spec)
```

## DSL layers

All functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
