package io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.repository

import io.github.alfonsoristorato.jpaspeckotlindsl.testfixtures.jpasetup.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository :
    JpaRepository<Comment, Long>,
    JpaSpecificationExecutor<Comment>
