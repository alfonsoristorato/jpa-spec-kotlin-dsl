plugins {
    id("jpa-dsl-module")
    id("jpa-dsl-publishing")
    `java-test-fixtures`
}

dokka {
    moduleName.set("JPA Specification Kotlin DSL")
    dokkaSourceSets.main {
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/tree/main/jpa-spec-kotlin-dsl/src/main/kotlin")
            remoteLineSuffix.set("#L")
        }
    }
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        outputDirectory.set(layout.buildDirectory.dir("docs"))
    }
}

publishing {
    publications {
        named<MavenPublication>("mavenJava") {
            pom {
                name.set("JPA Specification Kotlin DSL")
                description.set("A Kotlin DSL for building JPA Specifications with idiomatic syntax")
            }
        }
    }
}

/**
 * Exclude testFixtures variants from the published Maven POM.
 * Without this, java-test-fixtures adds testFixturesApiElements and testFixturesRuntimeElements
 * to the java component, causing testFixtures dependencies to appear as runtime dependencies in the published POM and leak to users.
 * See: https://docs.gradle.org/current/userguide/java_testing.html#publishing_test_fixtures
 */
val javaComponent = components["java"] as AdhocComponentWithVariants
javaComponent.withVariantsFromConfiguration(configurations["testFixturesApiElements"]) { skip() }
javaComponent.withVariantsFromConfiguration(configurations["testFixturesRuntimeElements"]) { skip() }

dependencies {
    testFixturesImplementation(libs.bundles.kotest)
    testFixturesImplementation(libs.bundles.db.test)
    testFixturesImplementation(libs.bundles.spring.test)
    testFixturesImplementation(libs.archunit.junit5)
}

kover {
    currentProject {
        sources {
            excludedSourceSets.add("testFixtures")
        }
    }
}
