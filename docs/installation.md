# Installation

## Core module

The core module works with any standard JPA provider (Hibernate, EclipseLink, etc.) and has no provider-specific dependencies.

=== "Gradle (Kotlin DSL)"

    ```kotlin
    dependencies {
        implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:{{ release_version }}")
    }
    ```

=== "Gradle (Groovy)"

    ```groovy
    dependencies {
        implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl:{{ release_version }}'
    }
    ```

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.alfonsoristorato</groupId>
        <artifactId>jpa-spec-kotlin-dsl</artifactId>
        <version>{{ release_version }}</version>
    </dependency>
    ```

## Core and Hibernate extensions

!!! warning "Hibernate required"
    The hibernate module only works when **Hibernate is your JPA provider**. It will not work with EclipseLink or any other provider.

    You must also declare the **core module explicitly** - the hibernate module does not pull it in transitively. Both dependencies are required.

=== "Gradle (Kotlin DSL)"

    ```kotlin
    dependencies {
        implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:{{ release_version }}")
        implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl-hibernate:{{ release_version }}")
    }
    ```

=== "Gradle (Groovy)"

    ```groovy
    dependencies {
        implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl:{{ release_version }}'
        implementation 'io.github.alfonsoristorato:jpa-spec-kotlin-dsl-hibernate:{{ release_version }}'
    }
    ```

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.alfonsoristorato</groupId>
        <artifactId>jpa-spec-kotlin-dsl</artifactId>
        <version>{{ release_version }}</version>
    </dependency>
    <dependency>
        <groupId>io.github.alfonsoristorato</groupId>
        <artifactId>jpa-spec-kotlin-dsl-hibernate</artifactId>
        <version>{{ release_version }}</version>
    </dependency>
    ```
