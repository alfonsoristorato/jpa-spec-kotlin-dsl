package io.github.alfonsoristorato.jpaspeckotlindsl.path

import io.github.alfonsoristorato.jpaspeckotlindsl.nested.NestedProperty
import jakarta.persistence.criteria.Path
import kotlin.reflect.KProperty1

/**
 * Resolves this property to its JPA [Path] for the given [path].
 *
 * @param T the type of the entity.
 * @param P the type of the property.
 * @param path The path of the entity.
 * @return The [Path] pointing at this property.
 */
fun <T, P> KProperty1<T, P>.path(path: Path<T>): Path<P> = path.get(this.name)

/**
 * Resolves this nested property to its JPA [Path] by traversing all intermediate
 * parent names and then the leaf child name, starting from the given [path].
 *
 * @param ROOT the root entity type.
 * @param PROP the type of the property.
 * @param path The JPA root or parent path to start from.
 * @return The [Path] pointing at this nested property.
 */
@Suppress("UNCHECKED_CAST")
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.path(path: Path<*>): Path<PROP> {
    val parentPath = parentNames.fold(path as Path<Any>) { currentPath, name -> currentPath.get(name) }
    return parentPath.get(child.name)
}
