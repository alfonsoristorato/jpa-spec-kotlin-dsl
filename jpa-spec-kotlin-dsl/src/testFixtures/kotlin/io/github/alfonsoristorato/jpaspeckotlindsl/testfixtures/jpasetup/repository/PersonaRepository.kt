package io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository

import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository :
    JpaRepository<Persona, Long>,
    JpaSpecificationExecutor<Persona>
