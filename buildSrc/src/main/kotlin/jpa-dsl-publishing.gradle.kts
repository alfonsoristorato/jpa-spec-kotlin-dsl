plugins {
    //region Plugins for publishing
    `maven-publish`
    signing
    id("java")
    id("org.jetbrains.dokka")
    //endregion
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
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

extensions.configure<SigningExtension> {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PASSPHRASE"),
    )
    sign(publishing.publications["mavenJava"])
}
