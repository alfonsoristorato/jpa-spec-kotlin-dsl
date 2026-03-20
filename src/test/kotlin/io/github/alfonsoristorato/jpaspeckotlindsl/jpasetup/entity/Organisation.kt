package io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity

import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table
class Organisation(
    val name: String,
    @Embedded
    val organisationInfo: OrganisationInfo,
    @ElementCollection
    val departments: Set<String> = emptySet(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organisation_id_sequence")
    @SequenceGenerator(name = "organisation_id_sequence", allocationSize = 1)
    var id: Long? = null
}

@Embeddable
class OrganisationInfo(
    @Embedded
    val addressInfo: AddressInfo,
    @Embedded
    val contactInfo: ContactInfo? = null,
)

@Embeddable
class AddressInfo(
    val street: String,
    val city: String,
    val isActive: Boolean,
)

@Embeddable
class ContactInfo(
    val email: String,
    val phoneNumber: String,
    val nickname: String? = null,
    val isVerified: Boolean? = null,
)
