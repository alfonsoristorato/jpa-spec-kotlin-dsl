# Migration Guide: raw Criteria API to jpa-spec-kotlin-dsl

## Approach

1. Make sure the specification under migration has a test that runs it against a real database and asserts on
   the returned rows. Write one first if it is missing.
2. Migrate one specification (or one function returning a specification) at a time.
3. Translate each `cb.xxx(root.get("field"), ...)` to the matching DSL call, choosing the layer by what the
   surrounding code expects (`Specification`, `PredicateSpecification` or raw `Predicate`).
4. Keep raw `CriteriaBuilder` calls for anything the DSL does not wrap, but replace `root.get("field")` with
   `Entity::field.path(root)` so the field access is still type-safe.
5. Run the tests; compare results against the old implementation if in doubt.

## Field access

| Before                                                       | After                                                          |
|--------------------------------------------------------------|----------------------------------------------------------------|
| `root.get<String>("name")`                                   | `User::name.path(root)`                                        |
| `root.get<AddressInfo>("addressInfo").get<String>("street")` | `(Organisation::addressInfo / AddressInfo::street).path(root)` |
| `root.join<Post, Persona>("persona")`                        | `Post::persona.join(root)`                                     |
| `root.join<Post, Persona>("persona", JoinType.LEFT)`         | `Post::persona.join(root, JoinType.LEFT)`                      |
| `root.fetch<Post, Persona>("persona")`                       | `Post::persona.fetch(root)`                                    |

## Operations

| `CriteriaBuilder` call                       | Specification / PredicateSpecification layer | Predicate layer                        |
|----------------------------------------------|----------------------------------------------|----------------------------------------|
| `cb.equal(x, v)` / `cb.notEqual(x, v)`       | `P.equal(v)` / `P.notEqual(v)`               | `P.equal(root, cb, v)`                 |
| `cb.greaterThan(x, v)`                       | `P.greaterThan(v)`                           | `P.greaterThan(root, cb, v)`           |
| `cb.greaterThanOrEqualTo(x, v)`              | `P.greaterThanOrEqualTo(v)`                  | `P.greaterThanOrEqualTo(root, cb, v)`  |
| `cb.lessThan(x, v)` / `cb.lessThanOrEqualTo` | `P.lessThan(v)` / `P.lessThanOrEqualTo(v)`   | `P.lessThan(root, cb, v)`              |
| `cb.between(x, a, b)`                        | `P.between(a, b)`                            | `P.between(root, cb, a, b)`            |
| `cb.like(x, p)` / `cb.notLike(x, p)`         | `P.like(p)` / `P.notLike(p)`                 | `P.like(root, cb, p)`                  |
| `cb.isNull(x)` / `cb.isNotNull(x)`           | `P.isNull()` / `P.isNotNull()`               | `P.isNull(root, cb)`                   |
| `cb.isTrue(x)` / `cb.isFalse(x)`             | `P.isTrue()` / `P.isFalse()`                 | `P.isTrue(root, cb)`                   |
| `x.in(values)` / `cb.in(x).value(v)`         | `P.containedIn(values)`                      | `P.containedIn(root, cb, values)`      |
| `cb.not(x.in(values))`                       | `P.notContainedIn(values)`                   | `P.notContainedIn(root, cb, values)`   |
| `cb.isEmpty(x)` / `cb.isNotEmpty(x)`         | `P.isEmpty()` / `P.isNotEmpty()`             | `P.isEmpty(root, cb)`                  |
| `cb.isMember(v, x)` / `cb.isNotMember(v, x)` | `P.isMember(v)` / `P.isNotMember(v)`         | `P.isMember(root, cb, v)`              |
| `cb.and(a, b, c)` / `cb.or(a, b, c)`         | `a and b and c` or `and(a, b, c)`            | `and(cb, a, b, c)` / `or(cb, a, b, c)` |
| `spec1.and(spec2)` / `spec1.or(spec2)`       | `spec1 and spec2` / `spec1 or spec2`         | -                                      |

`P` is a property reference (`User::age`) or a nested property (`(A::b / B::c)`).
With the Hibernate module: `hcb.ilike(x, p)` becomes `P.ilike(p)`, `hcb.arrayContains(x, v)` becomes
`P.arrayContains(v)`, etc.

## Worked examples

### Simple specification

Before:

```kotlin
fun activeAdmins(): Specification<User> =
    Specification { root, _, cb ->
        cb.and(
            cb.isTrue(root.get("isActive")),
            cb.equal(root.get<String>("role"), "ADMIN"),
        )
    }
```

After:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.bool.isTrue
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.combiner.and
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.equality.equal

fun activeAdmins(): Specification<User> = User::isActive.isTrue() and User::role.equal("ADMIN")
```

### Join

Before:

```kotlin
fun postsByPersonaName(name: String): Specification<Post> =
    Specification { root, _, cb ->
        val persona = root.join<Post, Persona>("persona")
        cb.equal(persona.get<String>("name"), name)
    }
```

After:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.join.joinWithPredicate

fun postsByPersonaName(name: String): Specification<Post> =
    Post::persona.joinWithPredicate { persona, cb -> Persona::name.equal(persona, cb, name) }
```

If the only condition is on one joined field and an inner join is fine, the nested form is shorter:
`(Post::persona / Persona::name).equal(name)`.

### Optional filters

Before:

```kotlin
fun search(filter: UserFilter): Specification<User> =
    Specification { root, _, cb ->
        val predicates = mutableListOf<Predicate>()
        filter.name?.let { predicates += cb.like(root.get("name"), "%$it%") }
        filter.minAge?.let { predicates += cb.greaterThanOrEqualTo(root.get("age"), it) }
        cb.and(*predicates.toTypedArray())
    }
```

After:

```kotlin
fun search(filter: UserFilter): Specification<User> =
    listOfNotNull(
        filter.name?.let { User::name.like("%$it%") },
        filter.minAge?.let { User::age.greaterThanOrEqualTo(it) },
    ).fold(Specification.unrestricted()) { acc, spec -> acc and spec }
```

### Logic the DSL does not wrap

Keep the raw call, make the field access type-safe:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.path.path

fun emailEqualsIgnoreCase(email: String): Specification<User> =
    Specification { root, _, cb -> cb.equal(cb.lower(User::email.path(root)), email.lowercase()) }
```
