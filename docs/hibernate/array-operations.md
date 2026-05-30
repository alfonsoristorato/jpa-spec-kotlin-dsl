# Native array operations

`arrayContains`, `arrayNotContains`, and `arrayIncludes` work on columns stored as database array types
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

## `arrayIncludes`

Checks whether a native array column contains all elements of a given sub-array:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withAllTags = Organisation::tags.arrayIncludes(arrayOf("important", "urgent"))

repository.findAll(withAllTags)
```

## `arrayNotIncludes`

Checks whether a native array column does not contain all elements of a given sub-array:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withoutAllTags = Organisation::tags.arrayNotIncludes(arrayOf("important", "urgent"))

repository.findAll(withoutAllTags)
```

## Nested properties

All functions work on nested properties via the `/` operator:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = (Persona::organisation / Organisation::tags).arrayContains("important")
val spec = (Persona::organisation / Organisation::tags).arrayIncludes(arrayOf("important", "urgent"))
val spec = (Persona::organisation / Organisation::tags).arrayNotIncludes(arrayOf("important", "urgent"))
```

## DSL layers

All functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
