package jpaspeckotlindsl.jpasetup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpaSetupApplication

fun main(args: Array<String>) {
    runApplication<JpaSetupApplication>(*args)
}
