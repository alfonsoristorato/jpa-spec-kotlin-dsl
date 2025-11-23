package jpaspeckotlindsl.jpasetup.testconfig

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer
import kotlin.apply

@TestConfiguration(proxyBeanMethods = false)
class PostgresContainer {
    @Bean
    @ServiceConnection
    fun postgresContainer() = PostgreSQLContainer<Nothing>("postgres:16.8").apply { start() }
}
