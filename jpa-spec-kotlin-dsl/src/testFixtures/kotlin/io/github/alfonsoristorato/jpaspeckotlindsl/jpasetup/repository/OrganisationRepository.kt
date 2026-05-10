package io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Organisation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OrganisationRepository :
    JpaRepository<Organisation, Long>,
    JpaSpecificationExecutor<Organisation>
