# API Reference: jpa-spec-kotlin-dsl (core)

Root package: `io.github.alfonsoristorato.jpaspeckotlindsl`

Every operation is a top-level extension function. Each category exists in three layer packages with the same
function names:

| Layer                  | Import pattern                                                                       | Shape                                                  |
|------------------------|--------------------------------------------------------------------------------------|--------------------------------------------------------|
| Specification          | `io.github.alfonsoristorato.jpaspeckotlindsl.specification.<category>.<fn>`          | `prop.fn(values...): Specification<T>`                 |
| PredicateSpecification | `io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.<category>.<fn>` | `prop.fn(values...): PredicateSpecification<T>`        |
| Predicate              | `io.github.alfonsoristorato.jpaspeckotlindsl.predicate.<category>.<fn>`              | `prop.fn(path, criteriaBuilder, values...): Predicate` |

- `prop` is either a `KProperty1<T, P>` (`User::name`) or a `NestedProperty<ROOT, P>` (`(A::b / B::c)`).
- Specification and PredicateSpecification layers require `T : Any`.
- In the Predicate layer, `path` is a `jakarta.persistence.criteria.Path<T>`: the `Root`, or the `From` / `Join`
  you received in a join lambda.

---

## Equality - `<layer>.equality`

| Function   | Spec / PredicateSpec      | Predicate                           |
|------------|---------------------------|-------------------------------------|
| `equal`    | `prop.equal(value: P)`    | `prop.equal(path, cb, value: P)`    |
| `notEqual` | `prop.notEqual(value: P)` | `prop.notEqual(path, cb, value: P)` |

```kotlin
User::role.equal("ADMIN")
User::isActive.notEqual(false)
```

## Comparison - `<layer>.comparison`

Receiver: `KProperty1<T, P?>` / `NestedProperty<ROOT, P?>` with `P : Comparable<P>` - nullable and non-null
properties both work. Numbers, strings, `LocalDate`, `Instant`, enums, etc.

| Function               | Spec / PredicateSpec               | Predicate                        |
|------------------------|------------------------------------|----------------------------------|
| `greaterThan`          | `(value: P)`                       | `(path, cb, value: P)`           |
| `greaterThanOrEqualTo` | `(value: P)`                       | `(path, cb, value: P)`           |
| `lessThan`             | `(value: P)`                       | `(path, cb, value: P)`           |
| `lessThanOrEqualTo`    | `(value: P)`                       | `(path, cb, value: P)`           |
| `between`              | `(lower: P, upper: P)` (inclusive) | `(path, cb, lower: P, upper: P)` |

```kotlin
User::age.between(18, 65)
Post::publishedAt.greaterThanOrEqualTo(LocalDate.of(2024, 1, 1))
```

## String - `<layer>.string`

Receiver: `String` or `String?` property. Patterns use SQL wildcards (`%` any sequence, `_` one character) -
add them yourself. Matching is case-sensitive in most databases; for case-insensitive matching use the Hibernate
module's `ilike`, or a raw `cb.lower(prop.path(root))`.

| Function  | Spec / PredicateSpec | Predicate                     |
|-----------|----------------------|-------------------------------|
| `like`    | `(pattern: String)`  | `(path, cb, pattern: String)` |
| `notLike` | `(pattern: String)`  | `(path, cb, pattern: String)` |

```kotlin
User::email.like("%@example.com")
User::name.notLike("%Smith%")
```

## Nullability - `<layer>.nullability`

| Function    | Spec / PredicateSpec | Predicate    |
|-------------|----------------------|--------------|
| `isNull`    | `()`                 | `(path, cb)` |
| `isNotNull` | `()`                 | `(path, cb)` |

## Boolean - `<layer>.bool`

Receiver: `Boolean` or `Boolean?` property. A `NULL` column matches neither `isTrue()` nor `isFalse()`.

| Function  | Spec / PredicateSpec | Predicate    |
|-----------|----------------------|--------------|
| `isTrue`  | `()`                 | `(path, cb)` |
| `isFalse` | `()`                 | `(path, cb)` |

## Inclusion - `<layer>.inclusion`

`value` can be a single value or a collection (`listOf(...)`, `setOf(...)`), which becomes `IN (...)`.

| Function         | Spec / PredicateSpec | Predicate           | Notes                     |
|------------------|----------------------|---------------------|---------------------------|
| `` `in` ``       | `(value)`            | `(path, cb, value)` | needs backticks (keyword) |
| `containedIn`    | `(value)`            | `(path, cb, value)` | alias of `in`, preferred  |
| `notIn`          | `(value)`            | `(path, cb, value)` | `NOT IN`                  |
| `notContainedIn` | `(value)`            | `(path, cb, value)` | alias of `notIn`          |

```kotlin
User::role.containedIn(listOf("ADMIN", "MANAGER"))
Post::persona.containedIn(listOf(persona1, persona2))   // entities work too
```

An empty collection produces `IN ()`, which some databases reject - skip the condition when the list is empty.

## Collection - `<layer>.collection`

Receiver: `KProperty1<T, P>` where `P : Collection<E>` - JPA-managed collections (`@ElementCollection`,
`@OneToMany`, `@ManyToMany`). For a single-column database array (`@JdbcTypeCode(SqlTypes.ARRAY)`) use the
Hibernate module instead.

| Function      | Spec / PredicateSpec | Predicate              |
|---------------|----------------------|------------------------|
| `isEmpty`     | `()`                 | `(path, cb)`           |
| `isNotEmpty`  | `()`                 | `(path, cb)`           |
| `isMember`    | `(value: E)`         | `(path, cb, value: E)` |
| `isNotMember` | `(value: E)`         | `(path, cb, value: E)` |

```kotlin
Post::tags.isMember("kotlin")
Post::tags.isEmpty()
```

## Combiner - `<layer>.combiner`

| Layer                  | Functions                                                                                                 |
|------------------------|-----------------------------------------------------------------------------------------------------------|
| Specification          | `infix Specification<T>.and(other)`, `infix .or(other)`, `and(vararg Specification<T>)`, `or(vararg ...)` |
| PredicateSpecification | same as above for `PredicateSpecification<T>`                                                             |
| Predicate              | `and(criteriaBuilder, vararg Predicate)`, `or(criteriaBuilder, vararg Predicate)`                         |

The vararg Specification / PredicateSpecification forms `reduce` their arguments and throw on an empty argument
list. The Predicate forms delegate to `cb.and(...)` / `cb.or(...)`.

## Nested properties - `io.github.alfonsoristorato.jpaspeckotlindsl.nested`

```kotlin
data class NestedProperty<ROOT, out PROP>(val parentNames: List<String>, val child: KProperty1<*, PROP>)

operator fun <T, E, P> KProperty1<T, E>.div(next: KProperty1<in E & Any, P>): NestedProperty<T, P>
operator fun <ROOT, E, P> NestedProperty<ROOT, E>.div(next: KProperty1<in E & Any, P>): NestedProperty<ROOT, P>
```

- Import `io.github.alfonsoristorato.jpaspeckotlindsl.nested.div`.
- `E & Any` means nullable intermediate properties (`Organisation?`) chain without special syntax.
- Resolution is `root.get(parent).get(...).get(child)`, so associations are joined implicitly (inner join).
- `NestedProperty.resolve(root)` is deprecated, use `path(root)`.

## Path accessor - `io.github.alfonsoristorato.jpaspeckotlindsl.path`

```kotlin
fun <T, P> KProperty1<T, P>.path(path: Path<T>): Path<P>
fun <ROOT, PROP> NestedProperty<ROOT, PROP>.path(path: Path<*>): Path<PROP>
```

Use it for anything the DSL does not wrap: `orderBy`, `groupBy`, `cb.lower`, `cb.coalesce`, `cb.function`,
`CriteriaUpdate.set`, selections.

## Joins - `<layer>.join` (Specification and PredicateSpecification layers only)

```kotlin
fun <T : Any, R> KProperty1<T, R>.joinWithPredicate(
    joinType: JoinType = JoinType.INNER,
    predicateBuilder: (From<T, R>, CriteriaBuilder) -> Predicate,
): Specification<T>   // or PredicateSpecification<T>

fun <T : Any, R> KProperty1<T, R>.joinWithPredicates(
    joinType: JoinType = JoinType.INNER,
    predicateBuilder: (From<T, R>, CriteriaBuilder) -> List<Predicate>,   // AND-ed together
): Specification<T>

fun <T : Any, R : Any> KProperty1<T, R?>.joinNullableWithPredicate(...)
fun <T : Any, R : Any> KProperty1<T, R?>.joinNullableWithPredicates(...)
```

The lambda builds predicates with the **Predicate layer**, passing the joined `From` as the path.
Join functions are defined on `KProperty1` only (not on `NestedProperty`); for a multi-hop join, nest a raw
`join(...)` inside the lambda or use the low-level `join` below.

## Fetch joins - `<layer>.fetch` (Specification and PredicateSpecification layers only)

`fetchJoinWithPredicate`, `fetchJoinWithPredicates`, `fetchJoinNullableWithPredicate`,
`fetchJoinNullableWithPredicates` - same signatures as the join functions. They register a fetch (eager load) and
a separate join for the predicates.

## Low-level join / fetch

```kotlin
// io.github.alfonsoristorato.jpaspeckotlindsl.join.join
fun <Z, T, R> KProperty1<T, R>.join(from: From<Z, T>, joinType: JoinType = JoinType.INNER): Join<T, R>

// io.github.alfonsoristorato.jpaspeckotlindsl.fetch.fetch
fun <Z, T, R> KProperty1<T, R>.fetch(from: From<Z, T>, joinType: JoinType = JoinType.INNER): Fetch<T, R>
```

Useful for multi-hop joins inside a custom lambda:

```kotlin
Specification<Comment> { root, _, cb ->
    val post = Comment::post.join(root)
    val persona = Post::persona.join(post)
    Persona::name.equal(persona, cb, "Alice")
}
```
