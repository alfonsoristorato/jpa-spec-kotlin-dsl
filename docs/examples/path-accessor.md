# Path Accessor

`path` resolves a `KProperty1`/`NestedProperty` to its JPA `Path` for a given root - the same resolution every
predicate extension already does internally, exposed so you can use it directly with raw `CriteriaBuilder`
calls.

## `KProperty1`

```kotlin
val spec =
    Specification<User> { root, _, criteriaBuilder ->
        criteriaBuilder.equal(User::name.path(root), "Jane")
    }
```

## `NestedProperty`

```kotlin
val spec =
    Specification<Organisation> { root, _, criteriaBuilder ->
        criteriaBuilder.equal(
            (Organisation::organisationInfo / OrganisationInfo::addressInfo / AddressInfo::street).path(root),
            "Main Street",
        )
    }
```
