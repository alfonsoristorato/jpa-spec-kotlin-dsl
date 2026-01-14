package io.github.alfonsoristorato.jpaspeckotlindsl.util

import org.springframework.jdbc.core.JdbcTemplate
import kotlin.collections.forEach

object GenericTestUtils {
    object DataCleanerUtils {
        /**
         * Deletes all records from the provided tables in the given order.
         * This is useful for cleaning up test data between spec runs.
         */
        fun cleanData(


            jdbcTemplate:    JdbcTemplate,
            tablesToSequences: List<Pair<String, String?>>,
        ) {
            tablesToSequences.forEach { (tableName, pkSequence) ->
                jdbcTemplate.execute("DELETE FROM $tableName")
                pkSequence?.let {
                    jdbcTemplate.execute("ALTER SEQUENCE $it RESTART WITH 1")
                }
            }
        }
    }
}
