---
name: jpa-spec-kotlin-dsl
description: >
  Helps write, migrate, and debug Spring Data JPA queries using jpa-spec-kotlin-dsl, a Kotlin DSL
  that builds type-safe Specification<T>, PredicateSpecification<T>, and raw JPA Predicate objects
  from Kotlin property references (User::name.equal("Alice")). Covers equality, comparison,
  string, nullability, boolean, inclusion and collection operations, nested properties with the
  `/` operator, joins and fetch joins, and/or combiners, the `path` accessor, and the Hibernate
  module (ilike, regex, native array and collection columns). Use when the user mentions
  jpa-spec-kotlin-dsl, io.github.alfonsoristorato, Kotlin JPA Specifications, JpaSpecificationExecutor
  with Kotlin, PredicateSpecification, or wants to replace stringly-typed root.get("field") /
  CriteriaBuilder code with type-safe Kotlin.
license: MIT
metadata:
  author: Alfonso Ristorato
  version: "1.0.0"
---

# JPA Specification Kotlin DSL

`jpa-spec-kotlin-dsl` provides extension functions on `KProperty1<T, P>` (and on `NestedProperty`) that
build JPA queries without string field names. It has no runtime overhead: every function is a thin
wrapper over the standard JPA `CriteriaBuilder`.

```kotlin
// Before
repository.findAll(
    Specification<User> { root, _, cb ->
        cb.and(cb.equal(root.get<String>("name"), "Alice"), cb.greaterThan(root.get("age"), 18))
    },
)

// After
repository.findAll(User::name.equal("Alice") and User::age.greaterThan(18))
```

Two artifacts, both under group `io.github.alfonsoristorato`:

| Artifact                        | Purpose                                                 |
|---------------------------------|---------------------------------------------------------|
| `jpa-spec-kotlin-dsl`           | Core DSL, works with any JPA provider                   |
| `jpa-spec-kotlin-dsl-hibernate` | Hibernate-only extensions, requires the core module too |

## Step 0: Analyze the Project

1. Read `build.gradle.kts` / `pom.xml` (and `gradle/libs.versions.toml` if present):
    - Is `jpa-spec-kotlin-dsl` already a dependency? Which version?
    - Is `jpa-spec-kotlin-dsl-hibernate` declared? If so, the core module must be declared too.
    - Which Spring Data JPA / Spring Boot version? `PredicateSpecification` requires Spring Data JPA 4.x (Spring Boot
      4.x). On older versions only the `Specification` and `Predicate` layers are usable.
    - Is Hibernate the JPA provider? The hibernate module fails at runtime with any other provider.
2. Find the repositories and check they extend `JpaSpecificationExecutor<T>`. Without it there is no
   `findAll(spec)`.
3. Find existing `Specification { root, query, cb -> ... }` lambdas and `root.get<...>("...")` calls -
   these are migration candidates.
4. Note the entity shapes you will query: nullable properties, `@Embedded` types, `@ManyToOne` /
   `@OneToOne` relationships (nullable or not), `@ElementCollection`, and
   `@JdbcTypeCode(SqlTypes.ARRAY)` columns. They decide which function variant to use.

### Classify the Task

| User Intent                                                                       | Path                               |
|-----------------------------------------------------------------------------------|------------------------------------|
| Add the library and write first queries                                           | **Path A** - Setup & First Queries |
| Pick the right layer (`Specification` vs `PredicateSpecification` vs `Predicate`) | **Path B** - Choosing a Layer      |
| Query embedded / associated properties                                            | **Path C** - Nested Properties     |
| Filter across relationships, eager load                                           | **Path D** - Joins & Fetch Joins   |
| Combine conditions, build dynamic filters                                         | **Path E** - Combining             |
| Replace existing `CriteriaBuilder` / `root.get("x")` code                         | **Path F** - Migration             |
| Case-insensitive / regex matching, native array or collection columns             | **Path G** - Hibernate Module      |
| Compile errors, ambiguity, runtime failures                                       | **Path H** - Troubleshooting       |

## Path A: Setup & First Queries

### Step 1: Add dependencies

```kotlin
// build.gradle.kts
dependencies {
    implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl:<version>")
    // Only if Hibernate is the JPA provider and you need Hibernate-specific operations:
    implementation("io.github.alfonsoristorato:jpa-spec-kotlin-dsl-hibernate:<version>")
}
```

Look up the latest version on Maven Central rather than guessing. Keep both artifacts on the same version.
The hibernate artifact does not bring the core one transitively - declare both.

### Step 2: Make the repository a specification executor

```kotlin
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User>
```

No other configuration is needed.

### Step 3: Write queries

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.combiner.and
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.comparison.greaterThan
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.equality.equal

val activeAdults = User::isActive.equal(true) and User::age.greaterThan(18)
repository.findAll(activeAdults)
```

**Imports matter.** The same function names (`equal`, `like`, `isNull`, `and`, ...) exist in three
packages, one per layer. Import from the layer whose return type you want (see Path B). Prefer explicit
imports; wildcard-importing two layers of the same category causes ambiguity errors.

### Operation catalog (core)

All operations exist on both `KProperty1<T, P>` and `NestedProperty<ROOT, P>`, and in all three layers.
Package suffix shown in brackets; prefix it with the layer package from Path B.

| Category (package)          | Functions                                                                                                | Property type                                        |
|-----------------------------|----------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| Equality (`equality`)       | `equal(v)`, `notEqual(v)`                                                                                | any                                                  |
| Comparison (`comparison`)   | `greaterThan`, `greaterThanOrEqualTo`, `lessThan`, `lessThanOrEqualTo`, `between(lo, hi)`                | `Comparable<P>` or `Comparable<P>?`                  |
| String (`string`)           | `like(pattern)`, `notLike(pattern)` - pattern uses SQL `%` / `_`                                         | `String` or `String?`                                |
| Nullability (`nullability`) | `isNull()`, `isNotNull()`                                                                                | any                                                  |
| Boolean (`bool`)            | `isTrue()`, `isFalse()`                                                                                  | `Boolean` or `Boolean?`                              |
| Inclusion (`inclusion`)     | `` `in`(v) ``, `containedIn(v)`, `notIn(v)`, `notContainedIn(v)` - accept a single value or a collection | any                                                  |
| Collection (`collection`)   | `isEmpty()`, `isNotEmpty()`, `isMember(e)`, `isNotMember(e)`                                             | `Collection<E>` (`@ElementCollection`, `@OneToMany`) |
| Combiner (`combiner`)       | infix `and` / `or`, vararg `and(...)` / `or(...)`                                                        | -                                                    |

`containedIn` / `notContainedIn` are aliases that avoid the backtick-escaped `in` keyword - prefer them.

See [references/API-REFERENCE.md](references/API-REFERENCE.md) for signatures and full import paths.

## Path B: Choosing a Layer

| Layer                      | Package prefix                                                       | Returns                                  | Use when                                                                                                                       |
|----------------------------|----------------------------------------------------------------------|------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| **Specification**          | `io.github.alfonsoristorato.jpaspeckotlindsl.specification`          | `Specification<T>`                       | Default. Pass to `JpaSpecificationExecutor.findAll/count/exists/...`                                                           |
| **PredicateSpecification** | `io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification` | `PredicateSpecification<T>`              | Spring Data JPA 4.x; query-independent conditions reusable in `Specification`, `UpdateSpecification` and `DeleteSpecification` |
| **Predicate**              | `io.github.alfonsoristorato.jpaspeckotlindsl.predicate`              | `jakarta.persistence.criteria.Predicate` | Inside your own `Specification { }` / `PredicateSpecification { }` lambda, or inside a join lambda                             |

Specification and PredicateSpecification functions take only the value (s):

```kotlin
User::age.greaterThan(18)                          // Specification<User> or PredicateSpecification<User>
```

Predicate functions additionally take the `Path` (a `Root`, `Join` or `From`) and the `CriteriaBuilder` first:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.greaterThan

Specification<User> { root, _, cb -> User::age.greaterThan(root, cb, 18) }
```

Layers mix freely: build raw predicates where you need the lambda (e.g. custom ordering, `query.distinct`)
and return a `Specification` that composes with the rest.

## Path C: Nested Properties

Kotlin has no chained `::` references, so the DSL provides the `/` operator, producing a type-safe
`NestedProperty<ROOT, P>` that supports every operation above:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.nested.div

(Organisation::addressInfo / AddressInfo::street).equal("Main Street")
(Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::city).isNotNull()
(Persona::organisation / Organisation::name).like("Acme%")   // nullable association, no special syntax
```

- **Always wrap the chain in parentheses** before calling the operation - `.` binds tighter than `/`.
- Works for `@Embedded` types and for single-valued associations (implicit join via `path.get`).
- Use explicit joins (Path D) instead when you need a `LEFT` join, a fetch join, or several conditions on
  the same joined row.

### `path` accessor

`path(root)` resolves a `KProperty1` / `NestedProperty` to its JPA `Path`, for raw `CriteriaBuilder` calls the DSL
does not wrap (ordering, `coalesce`, functions, `CriteriaUpdate.set`, ...):

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.path.path

Specification<User> { root, query, cb ->
    query.orderBy(cb.asc(User::name.path(root)))
    cb.equal(cb.lower(User::email.path(root)), "jane@example.com")
}
```

`NestedProperty.resolve(root)` is deprecated - use `path(root)`.

## Path D: Joins & Fetch Joins

Join functions live in the Specification and PredicateSpecification layers (`...specification.join`,
`...specification.fetch`, and the `predicatespecification` equivalents). The lambda receives the joined
`From` and the `CriteriaBuilder`, and returns **Predicate-layer** predicates:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.comparison.greaterThan
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.join.joinWithPredicate
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.join.joinWithPredicates

val commentsFromAlice = Comment::persona.joinWithPredicate { persona, cb ->
    Persona::name.equal(persona, cb, "Alice")
}

val postsByAdultAlice = Post::persona.joinWithPredicates { persona, cb ->   // list is AND-ed
    listOf(
        Persona::name.equal(persona, cb, "Alice"),
        Persona::age.greaterThan(persona, cb, 18),
    )
}
```

| Relationship property | Plain join                                                | Fetch join (also eager-loads)                                       |
|-----------------------|-----------------------------------------------------------|---------------------------------------------------------------------|
| non-null (`R`)        | `joinWithPredicate`, `joinWithPredicates`                 | `fetchJoinWithPredicate`, `fetchJoinWithPredicates`                 |
| nullable (`R?`)       | `joinNullableWithPredicate`, `joinNullableWithPredicates` | `fetchJoinNullableWithPredicate`, `fetchJoinNullableWithPredicates` |

- Use the `Nullable` variants for `R?` properties - the lambda then receives `From<T, R>` (non-null `R`).
- All accept an optional `JoinType` first argument, default `INNER`:
  `Comment::post.joinWithPredicate(JoinType.LEFT) { post, cb -> ... }`.
- Low-level building blocks `KProperty1.join(from, joinType)` (`...jpaspeckotlindsl.join.join`) and
  `KProperty1.fetch(from, joinType)` (`...jpaspeckotlindsl.fetch.fetch`) return the raw `Join` / `Fetch` for
  custom lambdas.
- Fetch joins are a JPA-level feature: be careful combining them with paging (`findAll(spec, pageable)` also
  runs a count query) and with collection-valued joins (duplicate root rows; consider `query.distinct(true)`).

## Path E: Combining

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.combiner.and
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.combiner.or

// Infix, parentheses control precedence
val spec = User::isActive.isTrue() and (User::role.equal("ADMIN") or User::age.greaterThanOrEqualTo(18))

// Vararg, avoids deep nesting
val eligible = and(User::isActive.isTrue(), User::age.greaterThanOrEqualTo(18), User::email.isNotNull())
```

For raw predicates inside a lambda, the Predicate-layer combiner takes the `CriteriaBuilder` first:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.combiner.or

PredicateSpecification<User> { root, cb ->
    or(cb, User::role.equal(root, cb, "ADMIN"), User::isPremium.isTrue(root, cb))
}
```

### Dynamic filters (optional criteria)

Specifications are plain values - build a list and reduce it. Spring's `Specification.unrestricted()`
(Spring Data JPA 4.x) is the neutral element when nothing applies:

```kotlin
fun search(filter: UserFilter): Specification<User> =
    listOfNotNull(
        filter.name?.let { User::name.like("%$it%") },
        filter.minAge?.let { User::age.greaterThanOrEqualTo(it) },
        filter.roles?.takeIf { it.isNotEmpty() }?.let { User::role.containedIn(it) },
    ).fold(Specification.unrestricted()) { acc, spec -> acc and spec }
```

The vararg `and(...)` / `or(...)` require at least one argument (they `reduce`), so guard empty lists.

## Path F: Migration from `CriteriaBuilder` / `root.get`

See [references/MIGRATION-GUIDE.md](references/MIGRATION-GUIDE.md) for the full mapping. Quick table:

| Raw JPA / Spring Data                                                             | DSL (Specification layer)                                                    |
|-----------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| `cb.equal(root.get<String>("name"), v)`                                           | `User::name.equal(v)`                                                        |
| `cb.greaterThan(root.get("age"), 18)`                                             | `User::age.greaterThan(18)`                                                  |
| `cb.between(root.get("age"), 18, 65)`                                             | `User::age.between(18, 65)`                                                  |
| `cb.like(root.get("email"), "%@x.com")`                                           | `User::email.like("%@x.com")`                                                |
| `cb.isNull(root.get<String>("email"))`                                            | `User::email.isNull()`                                                       |
| `cb.isTrue(root.get("isActive"))`                                                 | `User::isActive.isTrue()`                                                    |
| `root.get<String>("role").in(list)`                                               | `User::role.containedIn(list)`                                               |
| `cb.isMember("kotlin", root.get("tags"))`                                         | `Post::tags.isMember("kotlin")`                                              |
| `root.get<AddressInfo>("addressInfo").get<String>("street")`                      | `(Organisation::addressInfo / AddressInfo::street)`                          |
| `root.join<Post, Persona>("persona").let { cb.equal(it.get<String>("name"), v) }` | `Post::persona.joinWithPredicate { p, cb -> Persona::name.equal(p, cb, v) }` |
| `spec1.and(spec2)`                                                                | `spec1 and spec2`                                                            |

Migrate one specification at a time and keep the existing tests green. Anything the DSL does not wrap stays
as raw `CriteriaBuilder` code using `path(root)` for the field access.

## Path G: Hibernate Module

See [references/HIBERNATE-REFERENCE.md](references/HIBERNATE-REFERENCE.md) for the full guide.

Package prefix: `io.github.alfonsoristorato.jpaspeckotlindslhibernate` (note: no dot between `dsl` and
`hibernate`), with the same three layers (`predicate`, `specification`, `predicatespecification`).

| Category (package) | Functions                                                                                                                                       | Property type                                        | Opt-in |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|--------|
| `string`           | `ilike`, `notIlike`, `likeRegexp`, `notLikeRegexp`, `ilikeRegexp`, `notIlikeRegexp`                                                             | `String` / `String?`                                 | No     |
| `array`            | `arrayContains`, `arrayNotContains`, `arrayIncludes`, `arrayNotIncludes`, `arrayIntersects`, `arrayNotIntersects`                               | `Array<E>` with `@JdbcTypeCode(SqlTypes.ARRAY)`      | Yes    |
| `collection`       | `collectionContains`, `collectionNotContains`, `collectionIncludes`, `collectionNotIncludes`, `collectionIntersects`, `collectionNotIntersects` | `Collection<E>` with `@JdbcTypeCode(SqlTypes.ARRAY)` | Yes    |

Opt-in: `@OptIn(ExperimentalHibernateApi::class)` or `@file:OptIn(ExperimentalHibernateApi::class)`
(`io.github.alfonsoristorato.jpaspeckotlindslhibernate.internal.ExperimentalHibernateApi`). Missing opt-in is
a warning, not an error.

Do not confuse native array columns (Hibernate `collection*` functions) with `@ElementCollection` (core
`isMember` / `isEmpty`).

## Path H: Troubleshooting

See [references/KNOWN-ISSUES.md](references/KNOWN-ISSUES.md) for details. Most common:

- **Type mismatch: `Predicate` vs `Specification<T>`** (or `PredicateSpecification<T>`) - the function was imported
  from the wrong layer package. Fix the import.
- **Overload resolution ambiguity** - wildcard imports from both the `specification` and
  `predicatespecification` layers (their signatures are identical). Use explicit imports.
- **`No value passed for parameter ...` / argument type mismatch on `path`** - a Predicate-layer function was
  called without `(path, criteriaBuilder, ...)`, or a Specification-layer function was called with them (common
  inside join lambdas). Fix the import or the arguments.
- **Unresolved `div` / `/` not working** - import `io.github.alfonsoristorato.jpaspeckotlindsl.nested.div`, and
  wrap the chain in parentheses.
- **`infix modifier is required`** on `a and b` - import the DSL combiner for the layer
  (`...specification.combiner.and`).
- **Type mismatch on a join lambda for a nullable relationship** - use the `joinNullable*` / `fetchJoinNullable*`
  variant.
- **`IllegalStateException: jpa-spec-kotlin-dsl-hibernate requires Hibernate as the JPA provider`** - the
  hibernate module was used with a non-Hibernate provider.
- **`NoClassDefFoundError` for `io.github.alfonsoristorato.jpaspeckotlindsl...`** - the hibernate module is
  declared without the core module.

## Verification

1. The project compiles with no ambiguity or opt-in warnings you did not intend.
2. Each new or migrated specification is covered by a test that runs it against a real database (e.g. `@DataJpaTest` or
   Testcontainers) and asserts on the returned rows - criteria mistakes only show up
   when the query runs.
3. For migrations, the old and the new specification return the same rows for the same fixtures.
4. Enable SQL logging (`spring.jpa.show-sql=true` or `logging.level.org.hibernate.SQL=DEBUG`) to confirm joins
   and fetches produce the expected SQL.

If the project uses Kotest, write those tests with Kotest (`FunSpec`, `shouldContainExactlyInAnyOrder`, ...).

## Reference Files

- [API Reference](references/API-REFERENCE.md) - every core function, signature shape and import path
- [Hibernate Reference](references/HIBERNATE-REFERENCE.md) - Hibernate module setup, opt-in and operations
- [Migration Guide](references/MIGRATION-GUIDE.md) - raw `CriteriaBuilder` / `root.get` to DSL, with examples
- [Known Issues](references/KNOWN-ISSUES.md) - common compile and runtime problems and fixes
- Documentation site: https://alfonsoristorato.github.io/jpa-spec-kotlin-dsl/
- KDoc API reference: https://alfonsoristorato.github.io/jpa-spec-kotlin-dsl/api/
