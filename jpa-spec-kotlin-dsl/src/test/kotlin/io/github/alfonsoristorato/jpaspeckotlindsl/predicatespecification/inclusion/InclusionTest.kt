package io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.inclusion

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.AddressInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Organisation
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.OrganisationInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.OrganisationRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PersonaRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository.PostRepository
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.testconfig.SpringBootTestEnhanced
import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div
import io.github.alfonsoristorato.jpaspeckotlindsl.util.TestFixtures
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.datatest.withExpects
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@SpringBootTestEnhanced
class InclusionTest(
    private val personaRepository: PersonaRepository,
    private val postRepository: PostRepository,
    private val organisationRepository: OrganisationRepository,
) : ExpectSpec({
        val persona1 =
            TestFixtures.createPersona(
                name = "Persona 1",
                age = 20,
                firstLogin = true,
            )

        val persona2 =
            TestFixtures.createPersona(
                name = "Persona 2",
                age = 30,
            )

        val persona3 =
            TestFixtures.createPersona(
                name = "Persona 3",
                age = 40,
            )
        beforeSpec {
            personaRepository.saveAll(listOf(persona1, persona2, persona3))
            personaRepository.findAll() shouldHaveSize 3

            val post1 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 1",
                    persona = persona1,
                )
            val post2 =
                TestFixtures.createPost(
                    title = "Post 2 - Persona 1",
                    persona = persona1,
                )
            val post3 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 2",
                    persona = persona2,
                )
            val post4 =
                TestFixtures.createPost(
                    title = "Post 1 - Persona 3",
                    persona = persona3,
                )
            postRepository.saveAll(listOf(post1, post2, post3, post4))
            postRepository.findAll() shouldHaveSize 4

            val org1 =
                TestFixtures.createOrganisation(
                    name = "Org Alpha",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Main Street"),
                        ),
                )
            val org2 =
                TestFixtures.createOrganisation(
                    name = "Org Beta",
                    organisationInfo =
                        TestFixtures.createOrganisationInfo(
                            addressInfo = TestFixtures.createAddressInfo(street = "Oak Road"),
                        ),
                )
            organisationRepository.saveAll(listOf(org1, org2))
            organisationRepository.findAll() shouldHaveSize 2
        }

        context("in and containedIn for PredicateSpecification checks if property is in the given value") {
            withExpects(
                nameFn = { "${it.second} with single int" },
                Persona::age.`in`(30) to "in",
                Persona::age.containedIn(30) to "containedIn",
            ) { (spec, _) ->
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of int" },
                Persona::age.`in`(listOf(30, 40)) to "in",
                Persona::age.containedIn(listOf(30, 40)) to "containedIn",
            ) { (spec, _) ->
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[1].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
            }

            withExpects(
                nameFn = { "${it.second} with single bool" },
                Persona::firstLogin.`in`(true) to "in",
                Persona::firstLogin.containedIn(true) to "containedIn",
            ) { (spec, _) ->
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of bool" },
                Persona::firstLogin.`in`(listOf(true, false)) to "in",
                Persona::firstLogin.containedIn(listOf(true, false)) to "containedIn",
            ) { (spec, _) ->
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 3
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
                result[1].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
                result[2].apply {
                    name shouldBe "Persona 3"
                    age shouldBe 40
                }
            }

            withExpects(
                nameFn = { "${it.second} with single string" },
                Persona::name.`in`("Persona 1") to "in",
                Persona::name.containedIn("Persona 1") to "containedIn",
            ) { (spec, _) ->
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of string" },
                Persona::name.`in`(listOf("Persona 1", "Persona 2")) to "in",
                Persona::name.containedIn(listOf("Persona 1", "Persona 2")) to "containedIn",
            ) { (spec, _) ->
                val result = personaRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
                result[1].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
            }

            withExpects(
                nameFn = { "${it.second} with single declared type" },
                Post::persona.`in`(persona1) to "in",
                Post::persona.containedIn(persona1) to "containedIn",
            ) { (spec, _) ->
                val result = postRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].apply {
                    title shouldBe "Post 1 - Persona 1"
                }
                result[1].apply {
                    title shouldBe "Post 2 - Persona 1"
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of declared type" },
                Post::persona.`in`(listOf(persona1, persona2)) to "in",
                Post::persona.containedIn(listOf(persona1, persona2)) to "containedIn",
            ) { (spec, _) ->
                val result = postRepository.findAll(spec)
                result shouldHaveSize 3
                result[0].apply {
                    title shouldBe "Post 1 - Persona 1"
                }
                result[1].apply {
                    title shouldBe "Post 2 - Persona 1"
                }
                result[2].apply {
                    title shouldBe "Post 1 - Persona 2"
                }
            }
        }

        context("in and containedIn for PredicateSpecification checks if nested property is in the given value") {
            withExpects(
                nameFn = { "${it.second} with nested types" },
                (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                    .`in`("Main Street") to "in",
                (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                    .containedIn("Main Street") to "containedId",
            ) { (spec, _) ->
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 1
                result[0].name shouldBe "Org Alpha"
            }

            withExpects(
                nameFn = { "${it.second} with collection of nested types" },
                (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                    .`in`(listOf("Main Street", "Oak Road")) to "in",
                (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
                    .containedIn(listOf("Main Street", "Oak Road")) to "containedId",
            ) { (spec, _) ->
                val result = organisationRepository.findAll(spec)
                result shouldHaveSize 2
                result[0].name shouldBe "Org Alpha"
                result[1].name shouldBe "Org Beta"
            }
        }
    })
