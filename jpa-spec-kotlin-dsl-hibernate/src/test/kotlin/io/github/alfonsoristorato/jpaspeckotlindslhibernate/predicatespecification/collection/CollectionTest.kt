@file:OptIn(ExperimentalHibernateApi::class)

package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicatespecification.collection

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Organisation
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository.OrganisationRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.util.TestFixtures
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class CollectionTest(
    private val personaRepository: PersonaRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        beforeSpec {
            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org With Departments",
                    departments = setOf("engineering", "marketing"),
                    identifiers = setOf("identifier1", "identifier2"),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org Without Departments",
                    departments = emptySet(),
                )
            organisationRepository.saveAll(listOf(org1, org2))
            organisationRepository.findAll() shouldHaveSize 2

            val persona1 =
                TestFixtures.createPersona(
                    name = "Persona 1",
                    organisation = org1,
                )
            val persona2 =
                TestFixtures.createPersona(
                    name = "Persona 2",
                    organisation = org2,
                )
            personaRepository.saveAll(listOf(persona1, persona2))
            personaRepository.findAll() shouldHaveSize 2
        }

        context("arrayContains for PredicateSpecification tests whether an element is contained in a native array column") {
            expect("returns organisations where the identifier is contained") {
                val spec = Organisation::identifiers.arrayContains("identifier1")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Org With Departments"
            }
            expect("returns empty when identifier is not contained in any array") {
                val spec = Organisation::identifiers.arrayContains("nonexistent")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 0
            }
            expect("with nested types") {
                val spec = (Persona::organisation / Organisation::identifiers).arrayContains("identifier1")
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 1"
            }
        }

        context("arrayNotContains for PredicateSpecification tests whether an element is not contained in a native array column") {
            expect("returns organisations where the identifier is not contained") {
                val spec = Organisation::identifiers.arrayNotContains("identifier1")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Org Without Departments"
            }
            expect("returns all organisations when identifier doesn't exist in any array") {
                val spec = Organisation::identifiers.arrayNotContains("nonexistent")
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 2
            }
            expect("with nested types") {
                val spec = (Persona::organisation / Organisation::identifiers).arrayNotContains("identifier1")
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 2"
            }
        }
    })
