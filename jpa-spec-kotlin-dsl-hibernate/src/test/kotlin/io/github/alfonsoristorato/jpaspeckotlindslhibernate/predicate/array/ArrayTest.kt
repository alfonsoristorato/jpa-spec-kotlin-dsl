@file:OptIn(ExperimentalHibernateApi::class)

package io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.array

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
class ArrayTest(
    private val personaRepository: PersonaRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        beforeSpec {
            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org With Tags",
                    tags = arrayOf("tag1", "tag2"),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org Without Tags",
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

        context("arrayContains for Predicate tests whether an element is contained in a native array column") {
            expect("returns organisations where the tag is contained") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayContains(root, cb, "tag1")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org With Tags"
            }
            expect("returns empty when tag is not contained in any array") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayContains(root, cb, "nonexistent")
                    }
                result shouldHaveSize 0
            }
            expect("with nested types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        (Persona::organisation / Organisation::tags).arrayContains(root, cb, "tag1")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 1"
            }
        }

        context("arrayNotContains for Predicate tests whether an element is not contained in a native array column") {
            expect("returns organisations where the tag is not contained") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayNotContains(root, cb, "tag1")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org Without Tags"
            }
            expect("returns all organisations when tag doesn't exist in any array") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayNotContains(root, cb, "nonexistent")
                    }
                result shouldHaveSize 2
            }
            expect("with nested types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        (Persona::organisation / Organisation::tags).arrayNotContains(root, cb, "tag1")
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 2"
            }
        }

        context("arrayIncludes for Predicate tests whether a native array column contains all elements of a sub-array") {
            expect("returns organisations whose array contains all given elements") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayIncludes(root, cb, arrayOf("tag1", "tag2"))
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org With Tags"
            }
            expect("returns empty when not all elements are present") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayIncludes(root, cb, arrayOf("tag1", "nonexistent"))
                    }
                result shouldHaveSize 0
            }
            expect("with nested types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        (Persona::organisation / Organisation::tags).arrayIncludes(root, cb, arrayOf("tag1", "tag2"))
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 1"
            }
        }

        context("arrayNotIncludes for Predicate tests whether a native array column does not contain all elements of a sub-array") {
            expect("returns organisations whose array does not contain all given elements") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayNotIncludes(root, cb, arrayOf("tag1", "tag2"))
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org Without Tags"
            }
            expect("returns all organisations when no array fully contains all given elements") {
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        Organisation::tags.arrayNotIncludes(root, cb, arrayOf("tag1", "nonexistent"))
                    }
                result shouldHaveSize 2
            }
            expect("with nested types") {
                val result =
                    personaRepository.findAll { root, _, cb ->
                        (Persona::organisation / Organisation::tags).arrayNotIncludes(root, cb, arrayOf("tag1", "tag2"))
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Persona 2"
            }
        }
    })
