package jpaspeckotlindsl.predicatespecification.or

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import jpaspeckotlindsl.jpasetup.entity.Persona
import jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import jpaspeckotlindsl.predicatespecification.equality.equal
import jpaspeckotlindsl.util.TestFixtures
import org.springframework.data.jpa.domain.PredicateSpecification

@SpringBootTestEnhanced
class OrTest(
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

        expect("and combines PredicateSpecification") {
            // TODO: change below to new DSL once implemented
            val idLowerThan3 =
                PredicateSpecification<Persona> { root, criteriaBuilder ->
                    criteriaBuilder.lessThan(
                        root.get(Persona::id.name),
                        3L,
                    )
                }
            val lastNameEquals = Persona::lastName.equal("LastName")
            val result = personaRepository.findAll(idLowerThan3 or lastNameEquals)
            assertSoftly {
                result shouldHaveSize 3
                result[0].name shouldBe "Persona 1"
                result[1].name shouldBe "Persona 2"
                result[2].name shouldBe "Persona 3"
            }
        }
    })
