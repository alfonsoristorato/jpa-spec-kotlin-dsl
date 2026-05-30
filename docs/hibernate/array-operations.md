# Native array operations

`arrayContains` and `arrayNotContains` work on columns stored as database array types
(e.g. `VARCHAR[]`, `INT[]` in PostgreSQL), mapped with `@JdbcTypeCode(SqlTypes.ARRAY)`
and typed as `Array<E>` in Kotlin.

!!! warning "Opt-in required"
    These functions are annotated with `@ExperimentalHibernateApi` and require opt-in:
    ```kotlin
    @OptIn(ExperimentalHibernateApi::class)
    ```
    Or at file level: `@file:OptIn(ExperimentalHibernateApi::class)`

!!! tip "Collection-typed properties"
    If your property is typed as `List<E>`, `Set<E>`, or any other `Collection<E>`,
    use [collection operations](collection-operations.md) instead.

## Setup

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    @JdbcTypeCode(SqlTypes.ARRAY)
    val tags: Array<String>,
)
```

## `arrayContains`

Checks whether a native array column contains a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withTag = Organisation::tags.arrayContains("important")

repository.findAll(withTag)
```

## `arrayNotContains`

Checks whether a native array column does not contain a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withoutTag = Organisation::tags.arrayNotContains("important")

repository.findAll(withoutTag)
```

## Nested properties

Both functions work on nested properties via the `/` operator:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = (Persona::organisation / Organisation::tags).arrayContains("important")
```

## DSL layers

Both functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
