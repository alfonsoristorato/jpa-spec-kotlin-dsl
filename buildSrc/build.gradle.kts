plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    //region Plugins for main
    implementation(plugin(libs.plugins.kotlin.jvm))
    implementation(plugin(libs.plugins.gradle.ktlint))
    implementation(plugin(libs.plugins.kover))
    implementation(plugin(libs.plugins.dokka))
    //endregion

    //region Plugins for test
    implementation(plugin(libs.plugins.kotlin.jpa))
    implementation(plugin(libs.plugins.kotlin.spring))
    implementation(plugin(libs.plugins.kotest.gradle.plugin))
    //endregion
}


/**
 * Helper function that transforms a Gradle Plugin alias from a
 * Version Catalog into a valid dependency notation for buildSrc
 */
fun plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
