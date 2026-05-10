plugins {
    //region Plugins for main
    id("org.jetbrains.kotlin.jvm")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.kotlinx.kover")
    id("org.jetbrains.dokka")
    //endregion

    //region Plugins for test
    id("io.kotest")
    id("org.jetbrains.kotlin.plugin.jpa")
    id("org.jetbrains.kotlin.plugin.spring")
    //endregion
}

group = "io.github.alfonsoristorato"
version = System.getenv("RELEASE_VERSION") ?: "LOCAL-SNAPSHOT"

repositories {
    mavenCentral()
}

val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

dependencies {
    compileOnly(libs.findLibrary("kotlin-stdlib").get())
    compileOnly(libs.findLibrary("spring-boot-starter-data-jpa").get())

    testImplementation(libs.findBundle("kotest").get())
    testImplementation(libs.findBundle("db-test").get())
    testImplementation(libs.findBundle("spring-test").get())
    testImplementation(libs.findLibrary("archunit-junit5").get())
}

tasks.test {
    useJUnitPlatform()
    systemProperties["kotest.framework.config.fqn"] =
        "io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.kotest.KotestProjectConfig"
}

kotest {
    alwaysRerunTests.set(false)
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
                minBound(100)
            }
        }
    }
}
