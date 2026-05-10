# JPA Specification Kotlin DSL

[![Main](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/main.yml/badge.svg)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/main.yml)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.alfonsoristorato/jpa-spec-kotlin-dsl?style=flat-square&color=blue)](https://central.sonatype.com/artifact/io.github.alfonsoristorato/jpa-spec-kotlin-dsl)
[![GitHub Release](https://img.shields.io/github/v/release/alfonsoristorato/jpa-spec-kotlin-dsl?style=flat-square&color=blue)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/releases)

A Kotlin DSL for building type-safe JPA queries using idiomatic Kotlin syntax.

## Before and after

This is what a simple specification looks like with plain Spring Data JPA:

```kotlin
val spec = Specification<User> { root, query, cb ->
    val namePath = root.get<String>("name")
    val agePath = root.get<Int>("age")
    cb.and(
        cb.equal(namePath, "Alice"),
        cb.greaterThan(agePath, 18)
    )
}
repository.findAll(spec)
```

With the DSL:

```kotlin
repository.findAll(User::name.equal("Alice") and User::age.greaterThan(18))
```

## Features

- **Type-safe queries** using Kotlin property references (`::`) - no stringly-typed field names
- **Three DSL flavors** covering `Predicate`, `Specification<T>`, and `PredicateSpecification<T>`
- **Idiomatic Kotlin** syntax with infix operators and the `/` operator for nested properties
- **Composable** with `and` and `or` operators
- **Zero overhead** - pure extension functions on top of JPA, no proxies or runtime code generation

## Modules

| Artifact | Purpose |
|---|---|
| `jpa-spec-kotlin-dsl` | Core DSL - works with any JPA provider |
| `jpa-spec-kotlin-dsl-hibernate` | Hibernate-specific extensions, requires the core module |

See [Installation](installation.md) to get started, or jump straight to [Quick Start](getting-started.md).
