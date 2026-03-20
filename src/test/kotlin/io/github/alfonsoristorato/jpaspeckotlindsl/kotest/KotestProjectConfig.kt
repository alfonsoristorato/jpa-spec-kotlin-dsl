package io.github.alfonsoristorato.jpaspeckotlindsl.kotest

import io.github.alfonsoristorato.jpaspeckotlindsl.util.GenericTestUtils
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.spec.Spec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.getBean
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
            val jdbcTemplate = context.getBean<JdbcTemplate>()
            GenericTestUtils.DataCleanerUtils.cleanData(
                jdbcTemplate,
                TablesData.tablesToSequences,
            )
        }.onFailure {
            // we skip this if there is no bean definition for JdbcTemplate, as it means we are not running a Spring Test
            if (it !is NoSuchBeanDefinitionException) {
                throw RuntimeException(
                    "There is something wrong, quite likely in the order with which the tables are cleared - please fix it!",
                    it,
                )
            }
        }
    }
}

object TablesData {
    val tablesToSequences =
        listOf(
            "comment" to "comment_id_sequence",
            "post_tags" to null,
            "post" to "post_id_sequence",
            "persona" to "persona_id_sequence",
            "organisation_departments" to null,
            "organisation" to "organisation_id_sequence",
        )
}
