package io.github.alfonsoristorato.jpaspeckotlindsl.internal

@RequiresOptIn(
    message =
        "This API relies on Hibernate-specific functionality (HibernateCriteriaBuilder) " +
            "and uses an incubating Hibernate feature. It may change or be removed if the underlying " +
            "Hibernate API changes. It will throw a NoClassDefFoundError at runtime if Hibernate is " +
            "not on the classpath, or a ClassCastException if Hibernate is on the classpath but is " +
            "not the active JPA provider.",
    level = RequiresOptIn.Level.WARNING,
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
)
annotation class ExperimentalHibernateApi
