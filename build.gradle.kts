plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.gradle.ktlint)
    alias(libs.plugins.kotlin.jpa) // for test only
    alias(libs.plugins.kotlin.spring) // for test only
}

group = "alfonsoristorato"

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
