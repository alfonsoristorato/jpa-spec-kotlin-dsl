# Case-insensitive string matching

`ilike` and `notIlike` perform pattern matching ignoring case, using Hibernate's `HibernateCriteriaBuilder.ilike()`.

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

## `ilike`

Checks whether a string property matches a pattern, ignoring case:

```kotlin
val smiths = User::name.ilike("%smith%")

repository.findAll(smiths)
```

## `notIlike`

Checks whether a string property does not match a pattern, ignoring case:

```kotlin
val notSmiths = User::name.notIlike("%smith%")

repository.findAll(notSmiths)
```

## Nested properties

Both functions work on nested properties via the `/` operator:

```kotlin
val spec = (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street).ilike("main%")

repository.findAll(spec)
```

## DSL layers

Both functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
