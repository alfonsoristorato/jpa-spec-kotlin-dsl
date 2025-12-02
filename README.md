# JPA Specification Kotlin DSL

[![Main](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/main.yml/badge.svg)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/main.yml)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.alfonsoristorato/jpa-spec-kotlin-dsl?style=flat-square&color=blue)](https://central.sonatype.com/artifact/io.github.alfonsoristorato/jpa-spec-kotlin-dsl)
[![GitHub Release](https://img.shields.io/github/v/release/alfonsoristorato/jpa-spec-kotlin-dsl?style=flat-square&color=blue)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/releases)

A Kotlin DSL for building type-safe JPA queries using idiomatic Kotlin syntax. The library provides three distinct DSLs:

1. **Predicate DSL** - Build JPA `Predicate` objects for use within `Specification` or `PredicateSpecification`
2. **Specification DSL** - Build complete `Specification<T>` queries
3. **PredicateSpecification DSL** - Build complete `PredicateSpecification<T>` queries

This is achieved with zero runtime overhead by providing extension functions on Kotlin property references
`KProperty1<T, P>`, letting you write type-safe predicates directly from property references.

## Features

- **Three DSL flavors**:
    - **Predicate DSL** - For building predicates to use within `Specification {}` or `PredicateSpecification {}`
    - **Specification DSL** - Complete specifications
    - **PredicateSpecification DSL** - Complete predicate specifications
- **Type-safe queries** using Kotlin property references (`::`) - implemented as extension functions on
  `KProperty1<T, ?>`
- **Idiomatic Kotlin** syntax with infix operators
- **Composable specifications** with `and` and `or` operators
- **Zero overhead** - extension functions on top of JPA (no proxies or runtime code generation)
- **Mix and match** - Use predicates within specifications or use specifications standalone

## Before and after

Below is a short comparison showing how a simple specification looks with plain Spring Data JPA (without the DSL) and
how the same logic becomes concise using this Kotlin DSL.

Without the DSL:

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

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>'
}
```

### Maven

```xml

<dependency>
    <groupId>io.github.alfonsoristorato</groupId>
    <artifactId>jpa-spec-kotlin-dsl</artifactId>
    <version>version</version>
</dependency>
```

## Examples

**[See comprehensive examples](EXAMPLES.md)**

## API Reference

Full API Reference documentation is available at: **https://alfonsoristorato.github.io/jpa-spec-kotlin-dsl/**

The documentation includes:

- Complete API reference for all packages (`predicate`, `specification`, `predicatespecification`)
- Detailed KDoc for each function and extension
- Type information
- Source code links

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built on top of [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- Inspired by the need for more idiomatic Kotlin syntax in `JPA` `Specification` and `PredicateSpecification`.

## Support

If you encounter any issues or have questions,
please [open an issue](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/issues) on GitHub.
