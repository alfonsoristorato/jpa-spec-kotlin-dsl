package jpaspeckotlindsl.archunit

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import io.kotest.core.spec.style.ExpectSpec

class ArchUnitRulesRunner :
    ExpectSpec({
        val importedClasses by lazy {
            ClassFileImporter()
                .withImportOption(ImportOption.DoNotIncludeJars())
                .importPackages("jpaspeckotlindsl")
        }

        fun checkPackageIndependence(
            from: String,
            to: String,
            description: String,
        ) {
            expect(description) {
                noClasses()
                    .that()
                    .resideInAnyPackage(from)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(to)
                    .check(importedClasses)
            }
        }

        checkPackageIndependence(
            "..specification..",
            "..predicatespecification..",
            "specification must not depend on predicatespecification",
        )

        checkPackageIndependence(
            "..predicatespecification..",
            "..specification..",
            "predicatespecification must not depend on specification",
        )
    })
