# Collection Operations

These operations work on standard JPA-managed collections - typically mapped with `@ElementCollection` or `@OneToMany`.

```kotlin
@Entity
class Post(
    @Id val id: Long,
    val isPublished: Boolean,
    @ElementCollection
    val tags: List<String>
)
```

```kotlin
val postsWithoutTags = Post::tags.isEmpty()
val postsWithTags = Post::tags.isNotEmpty()

val kotlinPosts = Post::tags.isMember("kotlin")
val nonKotlinPosts = Post::tags.isNotMember("kotlin")
```
