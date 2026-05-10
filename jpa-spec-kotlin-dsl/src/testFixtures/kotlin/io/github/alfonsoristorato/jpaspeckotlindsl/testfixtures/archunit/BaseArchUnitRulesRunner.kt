package io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.archunit

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import io.kotest.core.spec.style.ExpectSpec

abstract class BaseArchUnitRulesRunner(
    rootPackage: String,
) : ExpectSpec({
        val importedClasses by lazy {
            ClassFileImporter()
                .withImportOption(ImportOption.DoNotIncludeJars())
                .importPackages(rootPackage)
        }

        fun checkPackageIndependence(
            description: String,
            from: String,
            to: List<String>,
        ) {
            expect(description) {
                noClasses()
                    .that()
                    .resideInAnyPackage(from)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(*to.toTypedArray())
                    .check(importedClasses)
            }
        }

        checkPackageIndependence(
            description = "specification must not depend on predicatespecification",
            from = "..specification..",
            to = listOf("..predicatespecification.."),
        )

        checkPackageIndependence(
            description = "predicatespecification must not depend on specification",
            from = "..predicatespecification..",
            to = listOf("..specification.."),
        )

        checkPackageIndependence(
            description = "predicate must not depend on specification or predicatespecification",
            from = "..predicate..",
            to = listOf("..specification..", "..predicatespecification.."),
        )
    })
