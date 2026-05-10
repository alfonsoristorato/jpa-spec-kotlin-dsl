package io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.testconfig

import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.JpaSetupApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [JpaSetupApplication::class])
@Import(PostgresContainer::class)
@Transactional
annotation class SpringBootTestEnhanced
