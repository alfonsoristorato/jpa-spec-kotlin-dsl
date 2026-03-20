package io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity

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
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organisation_id_sequence")
    @SequenceGenerator(name = "organisation_id_sequence", allocationSize = 1)
    var id: Long? = null
}
