# Native array operations

`arrayContains` and `arrayNotContains` work on columns stored as actual database array types
(e.g. `VARCHAR[]`, `INT[]` in PostgreSQL), mapped with `@JdbcTypeCode(SqlTypes.ARRAY)`.

!!! warning "Opt-in required"
    These functions are annotated with `@ExperimentalHibernateApi` and require opt-in:
    ```kotlin
    @OptIn(ExperimentalHibernateApi::class)
    val spec = Organisation::identifiers.arrayContains("id-123")
    ```
    Or at file level: `@file:OptIn(ExperimentalHibernateApi::class)`

!!! note "Different from `@ElementCollection`"
    `@ElementCollection` creates a separate join table and is supported by the core module's standard
    collection operations (`isMember`, `isEmpty`, etc.).
    Native array columns are a single column in the parent table and require Hibernate's
    `HibernateCriteriaBuilder.arrayContains()` - which is why they live in this module.

## Setup

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    @JdbcTypeCode(SqlTypes.ARRAY)
    val identifiers: List<String>
)
```

## `arrayContains`

Checks whether a native array column contains a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withIdentifier = Organisation::identifiers.arrayContains("id-123")

repository.findAll(withIdentifier)
```

## `arrayNotContains`

Checks whether a native array column does not contain a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withoutIdentifier = Organisation::identifiers.arrayNotContains("id-123")

repository.findAll(withoutIdentifier)
```

## Nested properties

Both functions work on nested properties via the `/` operator:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = (Persona::organisation / Organisation::identifiers).arrayContains("id-123")
```

## DSL layers

Both functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
