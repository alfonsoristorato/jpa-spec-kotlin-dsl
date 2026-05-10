# Nested Properties

Kotlin does not support chained `::` property references. The DSL provides a `/` operator to compose property references into a type-safe path that works with all DSL operations.

## The `/` operator

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    val name: String,
    @Embedded val addressInfo: AddressInfo,
)

@Embeddable
class AddressInfo(
    val street: String,
    val city: String,
)
```

```kotlin
val mainStreet = (Organisation::addressInfo / AddressInfo::street).equal("Main Street")
repository.findAll(mainStreet)
```

## Multi-level nesting

The `/` operator is left-associative, so chains of any depth work naturally:

```kotlin
@Entity
class Organisation(
    @Id val id: Long,
    val name: String,
    @Embedded val organisationInfo: OrganisationInfo,
)

@Embeddable
class OrganisationInfo(
    @Embedded val addressInfo: AddressInfo,
)

@Embeddable
class AddressInfo(
    val street: String,
    val city: String,
)
```

```kotlin
val deepSpec = (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street)
    .equal("Main Street")
```

## Nullable associations

The `/` operator handles nullable associations transparently:

```kotlin
@Entity
class Persona(
    @Id val id: Long,
    val name: String,
    @OneToOne(fetch = FetchType.LAZY)
    val organisation: Organisation?,
)
```

```kotlin
// organisation is nullable, but the DSL handles it - no special syntax needed
val spec = (Persona::organisation / Organisation::name).equal("Acme Corp")
```

## All operations work on nested properties

Every operation available on direct properties also works on nested properties:

```kotlin
// Equality
(Organisation::addressInfo / AddressInfo::street).equal("Main Street")
(Organisation::addressInfo / AddressInfo::street).notEqual("Main Street")

// Comparison
(Organisation::addressInfo / AddressInfo::street).greaterThan("M")
(Organisation::addressInfo / AddressInfo::street).between("A", "M")

// String
(Organisation::addressInfo / AddressInfo::street).like("Main%")
(Organisation::addressInfo / AddressInfo::street).notLike("Oak%")

// Nullability
(Organisation::addressInfo / AddressInfo::city).isNotNull()
(Organisation::addressInfo / AddressInfo::city).isNull()

// Inclusion
(Organisation::addressInfo / AddressInfo::street).`in`("Main Street")

// Combining nested specs
val spec = (Organisation::addressInfo / AddressInfo::street).like("Main%") and
        (Organisation::addressInfo / AddressInfo::city).isNotNull()
```

Nested property operations work with both `Specification` and `PredicateSpecification`.
