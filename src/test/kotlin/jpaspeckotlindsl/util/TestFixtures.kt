package jpaspeckotlindsl.util

import jpaspeckotlindsl.jpasetup.entity.Comment
import jpaspeckotlindsl.jpasetup.entity.Post
import jpaspeckotlindsl.jpasetup.entity.User

object TestFixtures {
    const val DEFAULT_USER_NAME = "Name"
    const val DEFAULT_USER_LAST_NAME = "LastName"
    const val DEFAULT_USER_AGE = 30
    const val DEFAULT_USER_USERNAME = "username"
    const val DEFAULT_POST_TITLE = "Default Post Title"
    const val DEFAULT_POST_CONTENT = "Default Post Content"
    const val DEFAULT_COMMENT_CONTENT = "Default Comment Content"

    fun createUser(
        name: String = DEFAULT_USER_NAME,
        lastName: String = DEFAULT_USER_LAST_NAME,
        age: Int = DEFAULT_USER_AGE,
        userName: String = DEFAULT_USER_USERNAME,
    ) = User(
        name = name,
        lastName = lastName,
        age = age,
        userName = userName,
    )

    fun createPost(
        user: User = createUser(),
        title: String = DEFAULT_POST_TITLE,
        content: String = DEFAULT_POST_CONTENT,
    ) = Post(
        user = user,
        title = title,
        content = content,
    )

    fun createComment(
        post: Post = createPost(),
        user: User = createUser(),
        content: String = DEFAULT_COMMENT_CONTENT,
    ) = Comment(
        post = post,
        user = user,
        content = content,
    )
}
