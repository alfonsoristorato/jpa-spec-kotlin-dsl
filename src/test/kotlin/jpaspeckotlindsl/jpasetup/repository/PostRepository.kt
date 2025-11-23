package jpaspeckotlindsl.jpasetup.repository

import jpaspeckotlindsl.jpasetup.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface PostRepository :
    JpaRepository<Post, Long>,
    JpaSpecificationExecutor<Post>
