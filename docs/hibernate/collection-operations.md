# Native collection operations

`collectionContains` and `collectionNotContains` work on columns stored as database array types
(e.g. `VARCHAR[]`, `INT[]` in PostgreSQL), mapped with `@JdbcTypeCode(SqlTypes.ARRAY)`
and typed as `Collection<E>` (e.g. `List<E>`, `Set<E>`) in Kotlin.

!!! warning "Opt-in required"
    These functions are annotated with `@ExperimentalHibernateApi` and require opt-in:
    ```kotlin
    @OptIn(ExperimentalHibernateApi::class)
    ```
    Or at file level: `@file:OptIn(ExperimentalHibernateApi::class)`

!!! tip "Array-typed properties"
    If your property is typed as `Array<E>`, use [array operations](array-operations.md) instead.

!!! note "Different from `@ElementCollection`"
    `@ElementCollection` creates a separate join table and is supported by the core module's standard
    collection operations (`isMember`, `isEmpty`, etc.).
    Native collection columns are a single column in the parent table and require Hibernate's
    `HibernateCriteriaBuilder.collectionContains()` - which is why they live in this module.

## Setup

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    @JdbcTypeCode(SqlTypes.ARRAY)
    val identifiers: Set<String>,
)
```

## `collectionContains`

Checks whether a native collection column contains a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withIdentifier = Organisation::identifiers.collectionContains("id-123")

repository.findAll(withIdentifier)
```

## `collectionNotContains`

Checks whether a native collection column does not contain a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withoutIdentifier = Organisation::identifiers.collectionNotContains("id-123")

repository.findAll(withoutIdentifier)
```

## Nested properties

Both functions work on nested properties via the `/` operator:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = (Persona::organisation / Organisation::identifiers).collectionContains("id-123")
```

## DSL layers

Both functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
