# Installation

## Core module

The core module works with any standard JPA provider (Hibernate, EclipseLink, etc.) and has no provider-specific dependencies.

=== "Gradle (Kotlin DSL)"

    ```kotlin
    dependencies {
        implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>")
    }
    ```

=== "Gradle (Groovy)"

    ```groovy
    dependencies {
        implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>'
    }
    ```

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.alfonsoristorato</groupId>
        <artifactId>jpa-spec-kotlin-dsl</artifactId>
        <version>VERSION</version>
    </dependency>
    ```

## Core + Hibernate extensions

!!! warning "Hibernate required"
    The hibernate module only works when **Hibernate is your JPA provider**. It will not work with EclipseLink or any other provider.

    You must also declare the **core module explicitly** - the hibernate module does not pull it in transitively. Both dependencies are required.

=== "Gradle (Kotlin DSL)"

    ```kotlin
    dependencies {
        implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>")
        implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl-hibernate:<version>")
    }
    ```

=== "Gradle (Groovy)"

    ```groovy
    dependencies {
        implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>'
        implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl-hibernate:<version>'
    }
    ```

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.alfonsoristorato</groupId>
        <artifactId>jpa-spec-kotlin-dsl</artifactId>
        <version>VERSION</version>
    </dependency>
    <dependency>
        <groupId>io.github.alfonsoristorato</groupId>
        <artifactId>jpa-spec-kotlin-dsl-hibernate</artifactId>
        <version>VERSION</version>
    </dependency>
    ```
