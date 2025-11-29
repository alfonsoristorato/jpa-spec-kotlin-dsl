package io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table
class Persona(
    val name: String,
    val lastName: String?,
    val age: Int,
    val userName: String,
    val firstLogin: Boolean,
    val isMagic: Boolean?,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_id_sequence")
    @SequenceGenerator(name = "persona_id_sequence", allocationSize = 1)
    var id: Long? = null
}
