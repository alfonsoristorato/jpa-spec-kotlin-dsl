package io.github.alfonsoristorato.jpaspeckotlindsl.util

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.AddressInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Comment
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.ContactInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Organisation
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.OrganisationInfo
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Persona
import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Post

object TestFixtures {
    const val DEFAULT_PERSONA_NAME = "Name"
    const val DEFAULT_PERSONA_LAST_NAME = "LastName"
    const val DEFAULT_PERSONA_AGE = 30
    const val DEFAULT_PERSONA_USERNAME = "username"
    const val DEFAULT_POST_TITLE = "Default Post Title"
    const val DEFAULT_POST_CONTENT = "Default Post Content"
    const val DEFAULT_COMMENT_CONTENT = "Default Comment Content"
    const val DEFAULT_ORGANISATION_NAME = "Default Organisation Name"
    const val DEFAULT_ORGANISATION_STREET = "Default Organisation Street"
    const val DEFAULT_ORGANISATION_CITY = "Default Organisation City"
    const val DEFAULT_ORGANISATION_EMAIL = "Default Organisation Email"
    const val DEFAULT_ORGANISATION_PHONE_NUMBER = "Default Organisation Phone"

    fun createOrganisation(
        name: String = DEFAULT_ORGANISATION_NAME,
        organisationInfo: OrganisationInfo = createOrganisationInfo(),
        departments: Set<String> = emptySet(),
    ) = Organisation(
        name = name,
        organisationInfo = organisationInfo,
        departments = departments,
    )

    fun createOrganisationInfo(
        addressInfo: AddressInfo = createAddressInfo(),
        contactInfo: ContactInfo? = contactInfo(),
    ) = OrganisationInfo(
        addressInfo = addressInfo,
        contactInfo = contactInfo,
    )

    fun createAddressInfo(
        street: String = DEFAULT_ORGANISATION_STREET,
        city: String = DEFAULT_ORGANISATION_CITY,
        isActive: Boolean = true,
    ) = AddressInfo(
        street = street,
        city = city,
        isActive = isActive,
    )

    fun contactInfo(
        email: String = DEFAULT_ORGANISATION_EMAIL,
        phoneNumber: String = DEFAULT_ORGANISATION_PHONE_NUMBER,
        nickname: String? = null,
        isVerified: Boolean? = null,
    ) = ContactInfo(
        email = email,
        phoneNumber = phoneNumber,
        nickname = nickname,
        isVerified = isVerified,
    )

    fun createPersona(
        name: String = DEFAULT_PERSONA_NAME,
        lastName: String? = DEFAULT_PERSONA_LAST_NAME,
        age: Int = DEFAULT_PERSONA_AGE,
        userName: String = DEFAULT_PERSONA_USERNAME,
        firstLogin: Boolean = false,
        isMagic: Boolean? = null,
        organisation: Organisation? = null,
    ) = Persona(
        name = name,
        lastName = lastName,
        age = age,
        userName = userName,
        firstLogin = firstLogin,
        isMagic = isMagic,
        organisation = organisation,
    )

    fun createPost(
        persona: Persona = createPersona(),
        title: String = DEFAULT_POST_TITLE,
        content: String = DEFAULT_POST_CONTENT,
        tags: Set<String> = emptySet(),
    ) = Post(
        persona = persona,
        title = title,
        content = content,
        tags = tags,
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
