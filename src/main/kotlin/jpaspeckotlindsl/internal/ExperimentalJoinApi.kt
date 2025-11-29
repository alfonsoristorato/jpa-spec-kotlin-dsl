package jpaspeckotlindsl.internal

@RequiresOptIn(
    message = "This join API is experimental and may change or be removed in future versions.",
    level = RequiresOptIn.Level.WARNING,
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
)
annotation class ExperimentalJoinApi
