plugins {
    alias(libs.plugins.gradle.nexus.publish.plugin)
    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}

dokka {
    moduleName.set("JPA Specification Kotlin DSL")
    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("docs"))
    }
}

dependencies {
    dokka(project(":jpa-spec-kotlin-dsl"))
    dokka(project(":jpa-spec-kotlin-dsl-hibernate"))
}

nexusPublishing {
    packageGroup.set("io.github.alfonsoristorato")
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username = System.getenv("OSSRH_USERNAME")
            password = System.getenv("OSSRH_PASSWORD")
        }
    }
}
