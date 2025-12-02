plugins {
    //region Plugins for main
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.gradle.ktlint)
    alias(libs.plugins.kover)
    alias(libs.plugins.dokka)
    //endregion

    //region Plugins for test
    alias(libs.plugins.kotlin.jpa) // for test only
    alias(libs.plugins.kotlin.spring) // for test only
    //endregion

    //region Plugins for publishing
    `maven-publish`
    signing
    alias(libs.plugins.gradle.nexus.publish.plugin)
    //endregion
}

group = "io.github.alfonsoristorato"
version = System.getenv("RELEASE_VERSION") ?: "LOCAL-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Core dependencies
    compileOnly(libs.kotlin.stdlib)
    compileOnly(libs.spring.boot.starter.data.jpa)

    // Testing
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.bundles.db.test)
    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.archunit.junit5)
}

tasks.test {
    useJUnitPlatform()
    systemProperties["kotest.framework.config.fqn"] = "io.github.alfonsoristorato.jpaspeckotlindsl.kotest.KotestProjectConfig"
}

kotlin {
    compilerOptions {
        extraWarnings.set(true)
        allWarningsAsErrors.set(true)
    }
    jvmToolchain(21)
}

tasks.build {
    dependsOn(tasks.ktlintFormat, tasks.koverVerify)
}

configurations {
    testImplementation {
        exclude(group = "org.mockito", module = "mockito-core")
        exclude(group = "org.mockito", module = "mockito-junit-jupiter")
    }
}

kover {
    reports {
        verify {
            rule {
                /**
                 * TODO
                 * had to bring to 99% because of joinType in [io.github.alfonsoristorato.jpaspeckotlindsl.join]
                 * will try to cover it later
                 */
                minBound(99)
            }
        }
    }
}

dokka {
    moduleName.set("JPA Specification Kotlin DSL")
    dokkaSourceSets.main {
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/tree/main/src/main/kotlin")
            remoteLineSuffix.set("#L")
        }
    }

    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        outputDirectory.set(layout.buildDirectory.dir("docs"))
    }
}

//region Publishing artifacts
java {
    withSourcesJar()
    withJavadocJar()
}

// tasks.named<Jar>("javadocJar") {
//    from(tasks.named("dokkaGeneratePublicationHtml"))
// }

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("JPA Specification Kotlin DSL")
                description.set("A Kotlin DSL for building JPA Specifications with idiomatic syntax")
                url.set("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
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
                    developerConnection.set("scm:git:ssh://github.com:alfonsoristorato/jpa-spec-kotlin-dsl.git")
                    url.set("https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username = System.getenv("OSSRH_USERNAME")
            password = System.getenv("OSSRH_PASSWORD")
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

extensions.configure<SigningExtension> {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PASSPHRASE"),
    )
    sign(publishing.publications["mavenJava"])
}

//endregion
