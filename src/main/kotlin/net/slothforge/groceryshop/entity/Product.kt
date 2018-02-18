package net.slothforge.groceryshop.entity

import javax.persistence.*
import javax.persistence.GenerationType.SEQUENCE

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = SEQUENCE)
        val id: Int = -1,

        val name: String = "",

        val unit: Unit = Unit.UNSPECIFIED,

        @Column(name = "price_per_unit")
        val price: Double = -1.0,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
        @JoinTable(name = "product_to_group",
                joinColumns = [(JoinColumn(name = "product_id"))],
                inverseJoinColumns = [(JoinColumn(name = "product_group_id"))]
        )
        val groups: List<ProductGroup> = ArrayList()
)