# Known Issues: jpa-spec-kotlin-dsl

Common problems and fixes when using the DSL.

---

## 1. Wrong layer imported

**Problem:** `Type mismatch: inferred type is Predicate but Specification<User> was expected` (or the reverse,
or `PredicateSpecification` vs `Specification`).

**Cause:** Every operation name exists in three packages - `...specification.*`, `...predicatespecification.*`,
`...predicate.*` - and the IDE auto-imported the wrong one.

**Fix:** Change the import to the layer you need:

```kotlin
// returns Specification<User>
import io.github.alfonsoristorato.jpaspeckotlindsl.specification.equality.equal
// returns PredicateSpecification<User>
import io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.equality.equal
// returns Predicate, needs (path, criteriaBuilder, value)
import io.github.alfonsoristorato.jpaspeckotlindsl.predicate.equality.equal
```

A file that builds join lambdas usually imports both the Specification layer (for the join function) and the
Predicate layer (for the predicates inside the lambda) - that is expected, they have different signatures.

---

## 2. Overload resolution ambiguity

**Problem:** `Overload resolution ambiguity` on `User::age.greaterThan(18)`.

**Cause:** Both `specification.comparison.*` and `predicatespecification.comparison.*` are imported (usually via
wildcards). Their signatures are identical apart from the return type.

**Fix:** Use explicit imports, from one of the two layers per file. If you genuinely need both in the same file,
use an import alias:

```kotlin
import io.github.alfonsoristorato.jpaspeckotlindsl.predicatespecification.equality.equal as predicateSpecEqual
```

---

## 3. `/` operator not resolved or applied to the wrong thing

**Problem:** `Unresolved reference: div`, or `Organisation::addressInfo / AddressInfo::street.equal("x")` fails to
compile.

**Fix:** Import `io.github.alfonsoristorato.jpaspeckotlindsl.nested.div` and wrap the chain in parentheses:

```kotlin
(Organisation::addressInfo / AddressInfo::street).equal("x")
```

---

## 4. `'infix' modifier is required` on `spec1 and spec2`

**Cause:** Without the DSL combiner import, Kotlin only sees Spring's member `Specification.and(...)`, which is not
infix.

**Fix:** `import io.github.alfonsoristorato.jpaspeckotlindsl.specification.combiner.and` (or `.or`, or the
`predicatespecification.combiner` equivalent).

---

## 5. Join lambda type mismatch on a nullable relationship

**Problem:** `Persona::organisation.joinWithPredicate { org, cb -> Organisation::name.equal(org, cb, "x") }` does not
compile when `organisation` is `Organisation?`.

**Fix:** Use the nullable variants - `joinNullableWithPredicate(s)` / `fetchJoinNullableWithPredicate(s)`. The
lambda then receives `From<Persona, Organisation>`.

---

## 6. Vararg `and()` / `or()` throws on empty input

**Problem:** `UnsupportedOperationException: Empty array can't be reduced.`

**Cause:** The Specification / PredicateSpecification vararg combiners reduce their arguments.

**Fix:** Guard the empty case, or fold from `Specification.unrestricted()` /
`PredicateSpecification.unrestricted()`:

```kotlin
specs.fold(Specification.unrestricted<User>()) { acc, spec -> acc and spec }
```

---

## 7. `IN` with an empty collection

**Problem:** SQL error or unexpected results for `User::role.containedIn(emptyList())`.

**Fix:** Skip the condition when the collection is empty
(`roles.takeIf { it.isNotEmpty() }?.let { User::role.containedIn(it) }`).

---

## 8. Fetch join problems

- **Paging:** `findAll(spec, pageable)` also runs a count query built from the same specification; JPA providers
  reject fetch joins in count queries. Use a plain join for paged queries, or load the association separately
  (entity graph, second query).
- **Duplicate root rows:** fetching or joining a collection-valued association (`@OneToMany`) multiplies root rows.
  Call `query.distinct(true)` in a custom `Specification` or restructure the query.

---

## 9. Hibernate module runtime errors

| Error                                                                                  | Cause                                         | Fix                                   |
|----------------------------------------------------------------------------------------|-----------------------------------------------|---------------------------------------|
| `IllegalStateException: jpa-spec-kotlin-dsl-hibernate requires Hibernate as the JPA provider.` | Non-Hibernate provider                 | Use core functions or switch provider |
| `NoClassDefFoundError: io/github/alfonsoristorato/jpaspeckotlindsl/...`                | Core module not declared                      | Add `jpa-spec-kotlin-dsl` dependency  |
| Opt-in warning on `array*` / `collection*`                                             | Missing `@OptIn(ExperimentalHibernateApi::class)` | Add the opt-in                    |
| `collection*` / `array*` fail on an `@ElementCollection` property                      | Those functions target single-column database arrays | Use core `isMember` / `isEmpty` |

---

## 10. Case-insensitive matching with the core module

`like` is case-sensitive on most databases. Without the Hibernate module, use the `path` accessor:

```kotlin
Specification<User> { root, _, cb -> cb.like(cb.lower(User::name.path(root)), "%smith%") }
```

With the Hibernate module: `User::name.ilike("%smith%")`.

---

## 11. Entities and Kotlin

Not specific to this DSL, but frequent in the same projects: JPA entities in Kotlin need the `kotlin-jpa`
(no-arg) and `kotlin-spring` / all-open plugins, otherwise Hibernate cannot instantiate or proxy them.
