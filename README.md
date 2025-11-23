# JPA Specification Kotlin DSL

[![Test](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/test.yml/badge.svg)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/test.yml)
[![Kotlin](https://img.shields.io/badge/kotlin-2.2.21-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Alpha](https://img.shields.io/badge/status-alpha-orange.svg)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/releases)

## API Reference

### `and` Operator

Combines two specifications with a logical AND operation.

**Example:**
```kotlin
repository.findAll(specA and specB)
```

### `or` Operator

Combines two specifications with a logical OR operation.

**Example:**
```kotlin
repository.findAll(specA or specB)
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built on top of [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- Inspired by the need for more idiomatic Kotlin syntax in JPA queries

## Support

If you encounter any issues or have questions, please [open an issue](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/issues) on GitHub.

