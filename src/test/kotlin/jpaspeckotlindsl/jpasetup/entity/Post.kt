package jpaspeckotlindsl.jpasetup.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table
class Post(
    @ManyToOne(fetch = FetchType.LAZY)
    val persona: Persona,
    val title: String,
    val content: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_sequence")
    @SequenceGenerator(name = "post_id_sequence", allocationSize = 1)
    var id: Long? = null
}
