plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.gradle.ktlint)
    alias(libs.plugins.kotest)
}

group = "alfonsoristorato"

repositories {
    mavenCentral()
}

dependencies {
    // Core dependencies
    implementation(libs.kotlin.stdlib)

    // Testing
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.mockk)
    testImplementation(libs.testcontainers)
}

tasks.test {
    dependsOn(tasks.jvmKotest)
}

tasks.build {
    dependsOn(tasks.ktlintFormat)
}
