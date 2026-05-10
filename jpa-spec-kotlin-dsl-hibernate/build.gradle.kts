plugins {
    id("jpa-dsl-module")
    id("jpa-dsl-publishing")
}

dokka {
    moduleName.set("JPA Specification Kotlin DSL - Hibernate")
    dokkaSourceSets.main {
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/tree/main/jpa-spec-kotlin-dsl-hibernate/src/main/kotlin")
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
                name.set("JPA Specification Kotlin DSL - Hibernate")
                description.set("Hibernate-specific extensions for the JPA Specification Kotlin DSL")
            }
        }
    }
}

dependencies {
    api(project(":jpa-spec-kotlin-dsl"))
    testImplementation(testFixtures(project(":jpa-spec-kotlin-dsl")))
}
