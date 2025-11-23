package jpaspeckotlindsl.util

import org.springframework.jdbc.core.JdbcTemplate
import kotlin.collections.forEach

object GenericTestUtils {
    object DataCleanerUtils {
        /**
         * Deletes all records from the provided tables in the given order.
         * This is useful for cleaning up test data between spec runs.
         */
        fun cleanData(
            jdbcTemplate: JdbcTemplate,
            tableNames: List<String>,
        ) {
            tableNames.forEach { tableName ->
                jdbcTemplate.execute("DELETE FROM $tableName")
            }
        }
    }
}
