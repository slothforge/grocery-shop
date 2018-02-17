package net.slothforge.groceryshop.entity

import javax.persistence.*
import javax.persistence.GenerationType.AUTO

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = AUTO)
        var id: Int = -1,

        var name: String,

        var unit: Unit,

        @Column(name = "price_per_unit")
        var price: Double,

        @ManyToMany
        @JoinTable(name = "product_to_group",
                joinColumns = [(JoinColumn(name = "product_id"))],
                inverseJoinColumns = [(JoinColumn(name = "product_group_id"))]
        )
        var groups: List<ProductGroup> = ArrayList()
)