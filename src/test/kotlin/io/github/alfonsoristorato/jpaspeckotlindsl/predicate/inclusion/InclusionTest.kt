package io.github.alfonsoristorato.jpaspeckotlindsl.predicate.inclusion

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
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Root

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

        context("in and containedIn for Predicate checks if property is in the given value") {
            withExpects(
                nameFn = { "${it.second} with single int" },
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::age.`in`(root, cb, 30) } to "in",
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::age.containedIn(root, cb, 30) } to "containedIn",
            ) { (predicate, _) ->
                val result =
                    personaRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 2"
                    age shouldBe 30
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of int" },
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::age.`in`(root, cb, listOf(30, 40)) } to "in",
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::age.containedIn(root, cb, listOf(30, 40)) } to "containedIn",
            ) { (predicate, _) ->
                val result =
                    personaRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
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
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::firstLogin.`in`(root, cb, true) } to "in",
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::firstLogin.containedIn(root, cb, true) } to "containedIn",
            ) { (predicate, _) ->
                val result =
                    personaRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of bool" },
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::firstLogin.`in`(root, cb, listOf(true, false)) } to "in",
                {
                    root: Root<Persona>,
                    cb: CriteriaBuilder,
                    ->
                    Persona::firstLogin.containedIn(root, cb, listOf(true, false))
                } to "containedIn",
            ) { (predicate, _) ->
                val result =
                    personaRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
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
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::name.`in`(root, cb, "Persona 1") } to "in",
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::name.containedIn(root, cb, "Persona 1") } to "containedIn",
            ) { (predicate, _) ->
                val result =
                    personaRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
                result shouldHaveSize 1
                result[0].apply {
                    name shouldBe "Persona 1"
                    age shouldBe 20
                }
            }

            withExpects(
                nameFn = { "${it.second} with collection of string" },
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::name.`in`(root, cb, listOf("Persona 1", "Persona 2")) } to "in",
                { root: Root<Persona>, cb: CriteriaBuilder -> Persona::name.containedIn(root, cb, listOf("Persona 1", "Persona 2")) } to
                    "containedIn",
            ) { (predicate, _) ->
                val result =
                    personaRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
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
                { root: Root<Post>, cb: CriteriaBuilder -> Post::persona.`in`(root, cb, persona1) } to "in",
                { root: Root<Post>, cb: CriteriaBuilder -> Post::persona.containedIn(root, cb, persona1) } to "containedIn",
            ) { (predicate, _) ->
                val result =
                    postRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
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
                { root: Root<Post>, cb: CriteriaBuilder -> Post::persona.`in`(root, cb, listOf(persona1, persona2)) } to "in",
                { root: Root<Post>, cb: CriteriaBuilder -> Post::persona.containedIn(root, cb, listOf(persona1, persona2)) } to
                    "containedIn",
            ) { (predicate, _) ->
                val result =
                    postRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
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

        context("in and containedIn for Predicate checks if nested property is in the given value") {
            withExpects(
                nameFn = { "${it.second} with nested types" },
                { root: Root<Organisation>, cb: CriteriaBuilder ->
                    (
                        Organisation::organisationInfo / OrganisationInfo::addressInfo /
                            AddressInfo::street
                    ).`in`(root, cb, "Main Street")
                } to "in",
                { root: Root<Organisation>, cb: CriteriaBuilder ->
                    (
                        Organisation::organisationInfo / OrganisationInfo::addressInfo /
                            AddressInfo::street
                    ).containedIn(root, cb, "Main Street")
                } to "containedId",
            ) { (predicate, _) ->
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
                result shouldHaveSize 1
                result[0].name shouldBe "Org Alpha"
            }

            withExpects(
                nameFn = { "${it.second} with collection of nested types" },
                { root: Root<Organisation>, cb: CriteriaBuilder ->
                    (
                        Organisation::organisationInfo / OrganisationInfo::addressInfo /
                            AddressInfo::street
                    ).`in`(root, cb, listOf("Main Street", "Oak Road"))
                } to "in",
                { root: Root<Organisation>, cb: CriteriaBuilder ->
                    (
                        Organisation::organisationInfo / OrganisationInfo::addressInfo /
                            AddressInfo::street
                    ).containedIn(root, cb, listOf("Main Street", "Oak Road"))
                } to "containedId",
            ) { (predicate, _) ->
                val result =
                    organisationRepository.findAll { root, _, cb ->
                        predicate(root, cb)
                    }
                result shouldHaveSize 2
                result[0].name shouldBe "Org Alpha"
                result[1].name shouldBe "Org Beta"
            }
        }
    })
