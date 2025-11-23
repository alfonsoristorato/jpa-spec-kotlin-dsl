package jpaspeckotlindsl.jpasetup.testconfig

import jpaspeckotlindsl.jpasetup.JpaSetupApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = [JpaSetupApplication::class])
@Import(PostgresContainer::class)
annotation class SpringBootTestEnhanced