# Hibernate Reference: jpa-spec-kotlin-dsl-hibernate

Hibernate-specific DSL extensions, built on `org.hibernate.query.criteria.HibernateCriteriaBuilder`.

Root package: `io.github.alfonsoristorato.jpaspeckotlindslhibernate` (one word - no dot before `hibernate`).
Same three layers as the core module:

| Layer                  | Import pattern                                                                                |
|------------------------|-----------------------------------------------------------------------------------------------|
| Specification          | `io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.<category>.<fn>`          |
| PredicateSpecification | `io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicatespecification.<category>.<fn>` |
| Predicate              | `io.github.alfonsoristorato.jpaspeckotlindslhibernate.predicate.<category>.<fn>`              |

Every function exists for `KProperty1` and `NestedProperty` receivers, and composes with core functions via the
core combiners (`and` / `or`).

## Requirements

1. **Hibernate must be the active JPA provider** (the default with Spring Boot's `spring-boot-starter-data-jpa`).
   Otherwise the `CriteriaBuilder` cast fails at query time with
   `IllegalStateException: jpa-spec-kotlin-dsl-hibernate requires Hibernate as the JPA provider.`
2. **Declare the core module too** - it is not transitive:

```kotlin
dependencies {
    implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>")
    implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl-hibernate:<version>")
}
```

## Opt-in

Array and collection functions are annotated `@ExperimentalHibernateApi` (they use incubating Hibernate APIs).
The level is `WARNING`, so forgetting the opt-in compiles with a warning.

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi

@OptIn(ExperimentalHibernateApi::class)
val spec = Organisation::tags.arrayContains("important")

// or for the whole file
@file:OptIn(ExperimentalHibernateApi::class)
```

String functions need no opt-in.

---

## String matching - `<layer>.string`

Receiver: `String` or `String?` property.

| Function                                | Meaning                                      |
|-----------------------------------------|----------------------------------------------|
| `ilike(pattern)` / `notIlike`           | case-insensitive `LIKE` (`%`, `_` wildcards) |
| `likeRegexp(regex)` / `notLikeRegexp`   | case-sensitive regular expression match      |
| `ilikeRegexp(regex)` / `notIlikeRegexp` | case-insensitive regular expression match    |

Regex syntax and support depend on the database dialect (on PostgreSQL these map to `~` and `~*`).

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.string.ilike
import io.github.alfonsoristorato.jpaspeckotlindslhibernate.specification.string.ilikeRegexp

User::name.ilike("%smith%")
(Organisation::addressInfo / AddressInfo::street).ilikeRegexp("^main")
```

## Native array columns - `<layer>.array` (opt-in)

Receiver: `KProperty1<T, Array<E>>` mapped with `@JdbcTypeCode(SqlTypes.ARRAY)` (e.g. PostgreSQL `VARCHAR[]`).

| Function             | Argument             | Matches rows where the column...       |
|----------------------|----------------------|----------------------------------------|
| `arrayContains`      | `value: E`           | contains `value`                       |
| `arrayNotContains`   | `value: E`           | does not contain `value`               |
| `arrayIncludes`      | `subArray: Array<E>` | contains all elements of `subArray`    |
| `arrayNotIncludes`   | `subArray: Array<E>` | does not contain all elements          |
| `arrayIntersects`    | `subArray: Array<E>` | shares at least one element ("any of") |
| `arrayNotIntersects` | `subArray: Array<E>` | shares no element                      |

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    @JdbcTypeCode(SqlTypes.ARRAY)
    val tags: Array<String>,
)

Organisation::tags.arrayIntersects(arrayOf("important", "urgent"))
```

## Native collection columns - `<layer>.collection` (opt-in)

Receiver: `KProperty1<T, Collection<E>>` (so `List<E>`, `Set<E>` work) mapped with `@JdbcTypeCode(SqlTypes.ARRAY)`.
Same semantics as the array functions, taking a `Collection<E>` argument:

`collectionContains(value)`, `collectionNotContains(value)`, `collectionIncludes(subCollection)`,
`collectionNotIncludes(subCollection)`, `collectionIntersects(subCollection)`, `collectionNotIntersects(subCollection)`

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    @JdbcTypeCode(SqlTypes.ARRAY)
    val identifiers: Set<String>,
)

Organisation::identifiers.collectionIncludes(setOf("id-123", "id-456"))
```

## Choosing between core collection, Hibernate array and Hibernate collection

| Mapping                                              | Kotlin type         | Use                             |
|------------------------------------------------------|---------------------|---------------------------------|
| `@ElementCollection` / `@OneToMany` (separate table) | `List<E>`, `Set<E>` | core `isMember`, `isEmpty`, ... |
| `@JdbcTypeCode(SqlTypes.ARRAY)` single column        | `Array<E>`          | Hibernate `array*`              |
| `@JdbcTypeCode(SqlTypes.ARRAY)` single column        | `List<E>`, `Set<E>` | Hibernate `collection*`         |
