package jpaspeckotlindsl.kotest

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.spec.Spec
import io.kotest.extensions.spring.SpringExtension
import jpaspeckotlindsl.util.GenericTestUtils
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.TestContextManager

object KotestProjectConfig : AbstractProjectConfig() {
    override val extensions: List<Extension> =
        listOf(
            SpringExtension(),
            DatabaseCleanerAfterSpecListener,
        )
}

object DatabaseCleanerAfterSpecListener : AfterSpecListener {
    override suspend fun afterSpec(spec: Spec) {
        runCatching {
            val manager = TestContextManager(spec::class.java)
            val context = manager.testContext.applicationContext
            val jdbcTemplate = context.getBean(JdbcTemplate::class.java)
            GenericTestUtils.DataCleanerUtils.cleanData(
                jdbcTemplate,
                AllTables.tablesList,
            )
        }
    }
}

object AllTables {
    val tablesList =
        listOf(
            "comment",
            "post",
            "user",
        )
}
