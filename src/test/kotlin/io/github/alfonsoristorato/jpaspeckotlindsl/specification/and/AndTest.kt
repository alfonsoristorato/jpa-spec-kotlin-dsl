package io.github.alfonsoristorato.jpaspeckotlindsl.specification.and

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.springframework.data.jpa.domain.Specification

@SpringBootTestEnhanced
class AndTest(
    private val personaRepository: PersonaRepository,
) : ExpectSpec({
        beforeSpec {
            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Persona 2",
                )
            val persona3 =
                TestFixtures.createPersona(
                    name = "Persona 3",
                )
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3
        }

        expect("and combines Specifications") {
            // TODO: change below to new DSL once implemented
            val idLowerThan3 =
                Specification<Persona> { root, _, criteriaBuilder ->
                    criteriaBuilder.lessThan(
                        root.get(Persona::id.name),
                        3L,
                    )
                }
            val lastNameEquals = Persona::lastName.equal("LastName")
            val result = personaRepository.findAll(idLowerThan3 and lastNameEquals)
            assertSoftly {
                result shouldHaveSize 2
                result[0].name shouldBe "Persona 1"
                result[1].name shouldBe "Persona 2"
            }
        }
    })
