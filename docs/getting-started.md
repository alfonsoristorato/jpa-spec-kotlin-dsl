# Quick Start

## Setup

Define your JPA entities and repositories as usual:

```kotlin
@Entity
class User(
    @Id val id: Long,
    val name: String,
    val age: Int,
    val email: String?,
    val role: String,
    val isActive: Boolean
)

interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User>
```

No additional configuration is needed. The DSL is available as soon as the dependency is on the classpath.

## Three DSL flavors

The library provides the same operations across three layers depending on what you want to return:

| DSL                            | Returns                     | Use when                                                              |
|--------------------------------|-----------------------------|-----------------------------------------------------------------------|
| **Specification DSL**          | `Specification<T>`          | You use `JpaSpecificationExecutor`                                    |
| **PredicateSpecification DSL** | `PredicateSpecification<T>` | You use `QuerydslPredicateExecutor` / Spring Data's predicate support |
| **Predicate DSL**              | `Predicate`                 | You need a raw predicate inside a custom `Specification {}` block     |

## Your first query

```kotlin
// Single condition
val activeUsers = User::isActive.equal(true)
repository.findAll(activeUsers)

// Combined conditions
val activeAdults = User::isActive.equal(true) and User::age.greaterThan(18)
repository.findAll(activeAdults)

// Reusable specs
fun activeUsers() = User::isActive.equal(true)
fun adults() = User::age.greaterThan(18)

repository.findAll(activeUsers() and adults())
```

## Next steps

- [Basic Operations](examples/basic-operations.md) - equality, comparison, string, nullability, bool, inclusion
- [Collection Operations](examples/collection-operations.md) - collection operations
- [Nested Properties](examples/nested-properties.md) - the `/` operator for embedded and associated properties
- [Joins](examples/joins.md) - querying across relationships
- [Combining Specifications](examples/combining.md) - `and`, `or`, multi-condition combiners
- [Hibernate Module](hibernate/index.md) - Hibernate-specific extensions
