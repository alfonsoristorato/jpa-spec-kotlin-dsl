package jpaspeckotlindsl.util

import jpaspeckotlindsl.jpasetup.entity.Comment
import jpaspeckotlindsl.jpasetup.entity.Post
import jpaspeckotlindsl.jpasetup.entity.Persona

object TestFixtures {
    const val DEFAULT_PERSONA_NAME = "Name"
    const val DEFAULT_PERSONA_LAST_NAME = "LastName"
    const val DEFAULT_PERSONA_AGE = 30
    const val DEFAULT_PERSONA_USERNAME = "username"
    const val DEFAULT_POST_TITLE = "Default Post Title"
    const val DEFAULT_POST_CONTENT = "Default Post Content"
    const val DEFAULT_COMMENT_CONTENT = "Default Comment Content"

    fun createPersona(
        name: String = DEFAULT_PERSONA_NAME,
        lastName: String = DEFAULT_PERSONA_LAST_NAME,
        age: Int = DEFAULT_PERSONA_AGE,
        userName: String = DEFAULT_PERSONA_USERNAME,
    ) = Persona(
        name = name,
        lastName = lastName,
        age = age,
        userName = userName,
    )

    fun createPost(
        persona: Persona = createPersona(),
        title: String = DEFAULT_POST_TITLE,
        content: String = DEFAULT_POST_CONTENT,
    ) = Post(
        persona = persona,
        title = title,
        content = content,
    )

    fun createComment(
        post: Post = createPost(),
        persona: Persona = createPersona(),
        content: String = DEFAULT_COMMENT_CONTENT,
    ) = Comment(
        post = post,
        persona = persona,
        content = content,
    )
}
