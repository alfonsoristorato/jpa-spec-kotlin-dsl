# Hibernate Module

`jpa-spec-kotlin-dsl-hibernate` provides DSL extensions that rely on Hibernate-specific APIs.

## Requirements

!!! danger "Hibernate must be your JPA provider"
    This module casts the standard `CriteriaBuilder` to Hibernate's `HibernateCriteriaBuilder` internally.
    If your application uses a different JPA provider (EclipseLink, OpenJPA, etc.), calling any function
    from this module will throw an `IllegalStateException` at runtime.
    The dependency will compile and link fine - the error only surfaces at runtime when a query is executed.

!!! danger "Core module must be declared separately"
    The hibernate module depends on the core module, hence the core module
    is **not transitive**. You must declare both dependencies explicitly in your build. If the core module
    is missing, you will get a `NoClassDefFoundError` at runtime.

    See [Installation](../installation.md#core-and-hibernate-extensions) for the correct dependency declarations.

## Opt-in annotation

Some functions in this module are annotated with `@ExperimentalHibernateApi`. You must opt in at the call site:

```kotlin
@OptIn(ExperimentalHibernateApi::class)
val spec = Organisation::identifiers.collectionContains("id-123")
```

Or opt in at the file level:

```kotlin
@file:OptIn(ExperimentalHibernateApi::class)
```

The annotation is `RequiresOptIn.Level.WARNING`, so forgetting it produces a compiler warning, not an error.
Each operation page indicates whether opt-in is required.

## Operations

| Page | Functions | Opt-in required |
|---|---|---|
| [Case-insensitive string matching](string-operations.md) | `ilike`, `notIlike` | No |
| [Native array operations](array-operations.md) | `arrayContains`, `arrayNotContains` | Yes |
| [Native collection operations](collection-operations.md) | `collectionContains`, `collectionNotContains` | Yes |
