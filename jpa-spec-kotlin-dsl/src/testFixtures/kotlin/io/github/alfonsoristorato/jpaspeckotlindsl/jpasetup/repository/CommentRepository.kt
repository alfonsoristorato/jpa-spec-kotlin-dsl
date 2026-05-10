package io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.repository

import io.github.alfonsoristorato.jpaspeckotlindsl.jpasetup.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository :
    JpaRepository<Comment, Long>,
    JpaSpecificationExecutor<Comment>
