# Hibernate Module

`jpa-spec-kotlin-dsl-hibernate` provides DSL extensions that rely on Hibernate-specific APIs.

## Requirements

!!! danger "Hibernate must be your JPA provider"
    This module casts the standard `CriteriaBuilder` to Hibernate's `HibernateCriteriaBuilder` internally.
    If your application uses a different JPA provider (EclipseLink, OpenJPA, etc.), calling any function
    from this module will throw a `ClassCastException` at runtime. The dependency will compile and link
    fine - the error only surfaces at runtime when a query is executed.

!!! danger "Core module must be declared separately"
    The hibernate module depends on the core module, hence the core module
    is **not transitive**. You must declare both dependencies explicitly in your build. If the core module
    is missing, you will get a `NoClassDefFoundError` at runtime.

    See [Installation](../installation.md#core-hibernate-extensions) for the correct dependency declarations.

## Opt-in annotation

All functions in this module are annotated with `@ExperimentalHibernateApi`. You must opt in at the call site:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = Organisation::identifiers.arrayContains("id-123")
```

Or opt in at the file level:

```kotlin
@file:OptIn(ExperimentalHibernateApi::class)
```

The annotation is `RequiresOptIn.Level.WARNING`, so forgetting it produces a compiler warning, not an error.
This is intentional - the APIs are incubating and may change if the underlying Hibernate API changes.

## What's in this module

### Native array operations

Operations for columns stored as actual database array types (e.g. `VARCHAR[]`, `INT[]` in PostgreSQL),
mapped with `@JdbcTypeCode(SqlTypes.ARRAY)`.

!!! note "Different from `@ElementCollection`"
    `@ElementCollection` creates a separate join table and is supported by the core module's standard
    collection operations (`isMember`, `isEmpty`, etc.).
    Native array columns are a single column in the parent table and require Hibernate's
    `HibernateCriteriaBuilder.arrayContains()` - which is why they live in this module.

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    @JdbcTypeCode(SqlTypes.ARRAY)
    val identifiers: List<String>
)
```

#### `arrayContains`

Checks whether a native array column contains a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withIdentifier = Organisation::identifiers.arrayContains("id-123")

repository.findAll(withIdentifier)
```

#### `arrayNotContains`

Checks whether a native array column does not contain a given element:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val withoutIdentifier = Organisation::identifiers.arrayNotContains("id-123")

repository.findAll(withoutIdentifier)
```

Both functions work on direct properties and on nested properties via the `/` operator:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = (Persona::organisation / Organisation::identifiers).arrayContains("id-123")
```

Both functions are available in all three DSL layers: `Specification`, `PredicateSpecification`, and raw `Predicate`.
