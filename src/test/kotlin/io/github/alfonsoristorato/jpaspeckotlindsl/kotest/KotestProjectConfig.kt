package io.github.alfonsoristorato.jpaspeckotlindsl.kotest

import io.github.alfonsoristorato.jpaspeckotlindsl.util.GenericTestUtils
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.spec.Spec
import io.kotest.extensions.spring.SpringExtension
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
                TablesData.tablesToSequences,
            )
        }
    }
}

object TablesData {
    val tablesToSequences =
        listOf(
            "comment" to "comment_id_sequence",
            "post" to "post_id_sequence",
            "persona" to "persona_id_sequence",
        )
}
