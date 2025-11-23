plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.gradle.ktlint)
    alias(libs.plugins.kotlin.jpa) // for test only
    alias(libs.plugins.kotlin.spring) // for test only
    `maven-publish`
}

group = "alfonsoristorato"
version = System.getenv("RELEASE_VERSION") ?: "LOCAL-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Core dependencies
    api(libs.kotlin.stdlib)
    compileOnly(libs.spring.data.jpa)

    // Testing
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.h2)
    testImplementation(libs.bundles.spring.test)
}

tasks.test {
    useJUnitPlatform()
    systemProperties["kotest.framework.config.fqn"] = "jpaspeckotlindsl.kotest.KotestProjectConfig"
}

kotlin {
    compilerOptions {
        extraWarnings.set(true)
        allWarningsAsErrors.set(true)
    }
}

tasks.build {
    dependsOn(tasks.ktlintFormat)
}

configurations {
    all {
        exclude(group = "org.mockito", module = "mockito-core")
        exclude(group = "org.mockito", module = "mockito-junit-jupiter")
    }
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])

            pom {
                name.set("JPA Specification Kotlin DSL")
                description.set("A Kotlin DSL for building JPA Specifications with idiomatic syntax")
                url.set("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/blob/main/LICENSE")
                    }
                }

                developers {
                    developer {
                        id.set("alfonsoristorato")
                        name.set("Alfonso Ristorato")
                        url.set("https://github.com/alfonsoristorato")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/alfonsoristorato/jpa-spec-kotlin-dsl.git")
                    developerConnection.set("scm:git:ssh://github.com/alfonsoristorato/jpa-spec-kotlin-dsl.git")
                    url.set("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl")
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/alfonsoristorato/jpa-spec-kotlin-dsl")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
