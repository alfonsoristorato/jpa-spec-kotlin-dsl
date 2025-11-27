# JPA Specification Kotlin DSL

[![Main](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/main.yml/badge.svg)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/actions/workflows/main.yml)
[![Alpha](https://img.shields.io/badge/status-alpha-orange.svg)](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/releases)

A Kotlin DSL for building type-safe JPA Specifications and PredicateSpecifications using idiomatic Kotlin syntax. Write cleaner, more maintainable JPA Specifications with the power of Kotlin's type system.

## Features

-  **Type-safe queries** using Kotlin property references (`::`)
-  **Idiomatic Kotlin** syntax with infix operators
-  **Composable specifications** with `and` and `or` operators
-  **Works with Spring Data JPA** Specification and PredicateSpecification
-  **Zero overhead** - just extension functions on top of JPA

## Installation

### GitHub Packages (this is temporary - library will be soon switched to Maven Central)

Add the GitHub Packages repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/alfonsoristorato/jpa-spec-kotlin-dsl")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.token") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:version")
}
```

Create a `gradle.properties` file in your project root or `~/.gradle/gradle.properties`:

```properties
gpr.user=your-github-username
gpr.token=your-github-pat-token
```

**Note:** To generate a GitHub token, go to Settings → Developer settings → Personal access tokens → Generate new token. Select the `read:packages` scope.

## Quick Start

```kotlin
import jpaspeckotlindsl.specification.equality.equal
import jpaspeckotlindsl.specification.and.and
import jpaspeckotlindsl.specification.or.or

// Define your entity
@Entity
data class User(
    @Id val id: Long,
    val name: String,
    val age: Int,
    val isActive: Boolean
)

// Use the DSL in your repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User>

// Build type-safe queries
class UserService(private val userRepository: UserRepository) {
    
    fun findActiveAdults(): List<User> {
        val spec = User::isActive.equal(true) and User::age.equal(18)
        return userRepository.findAll(spec)
    }
    
    fun findYoungOrSenior(): List<User> {
        val spec = User::age.equal(25) or User::age.equal(65)
        return userRepository.findAll(spec)
    }
}
```

## API Reference

Full API Reference documentation is available at: **https://alfonsoristorato.github.io/jpa-spec-kotlin-dsl/**

The documentation includes:
- Complete API reference for all packages (`predicate`, `specification`, `predicatespecification`)
- Detailed KDoc for each function and extension
- Type information and usage examples
- Source code links

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built on top of [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- Inspired by the need for more idiomatic Kotlin syntax in JPA Specification and PredicateSpecification.

## Support

If you encounter any issues or have questions, please [open an issue](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/issues) on GitHub.
