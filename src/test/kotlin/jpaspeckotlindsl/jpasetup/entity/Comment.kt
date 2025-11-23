package jpaspeckotlindsl.jpasetup.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    val post: Post,
    @OneToOne(fetch = FetchType.LAZY)
    val persona: Persona,
    val content: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_sequence")
    @SequenceGenerator(name = "comment_id_sequence", allocationSize = 1)
    var id: Long? = null
}
