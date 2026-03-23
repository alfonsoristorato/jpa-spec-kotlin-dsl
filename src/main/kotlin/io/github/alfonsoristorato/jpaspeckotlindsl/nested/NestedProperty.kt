package io.github.alfonsoristorato.jpaspeckotlindsl.nested

import jakarta.persistence.criteria.Path
import kotlin.reflect.KProperty1

/**
 * Represents a multi-level nested property path for JPA Criteria queries.
 *
 * [ROOT] is always preserved as the root entity type regardless of chain depth.
 *
 * @receiver [ROOT]  The root entity type.
 * @receiver [PROP]  The final leaf property type.
 * @property parentNames  All intermediate field names, in traversal order.
 * @property child  The leaf [KProperty1], carrying its name and type [PROP].
 */
data class NestedProperty<ROOT, out PROP>(
    val parentNames: List<String>,
    val child: KProperty1<*, PROP>,
) {
    /**
     * Resolves the full JPA [Path] from a root path by traversing all intermediate
     * parent names and then the leaf child name.
     *
     * @param root The JPA root or parent path to start from.
     * @return The fully resolved [Path] pointing to the leaf property.
     */
    @Suppress("UNCHECKED_CAST")
    fun resolve(root: Path<*>): Path<@UnsafeVariance PROP> {
        val parentPath = parentNames.fold(root as Path<Any>) { path, name -> path.get(name) }
        return parentPath.get(child.name)
    }
}

/**
 * Starts a nested property chain from two [KProperty1] references.
 *
 * @return a [NestedProperty] with the first property as the initial [NestedProperty.parentNames]
 * and [next] as [NestedProperty.child].
 */
operator fun <T, E, P> KProperty1<T, E>.div(next: KProperty1<in E & Any, P>): NestedProperty<T, P> = NestedProperty(listOf(this.name), next)

/**
 * Extends an existing [NestedProperty] chain by one more level, keeping [ROOT] fixed.
 *
 * @return a new [NestedProperty] with the old child property appended to [NestedProperty.parentNames]
 * and [next] as [NestedProperty.child].
 */
operator fun <ROOT, E, P> NestedProperty<ROOT, E>.div(next: KProperty1<in E & Any, P>): NestedProperty<ROOT, P> =
    NestedProperty(parentNames + child.name, next)
