rootProject.name = "jpa-spec-kotlin-dsl"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
include(":jpa-spec-kotlin-dsl")
include(":jpa-spec-kotlin-dsl-hibernate")
