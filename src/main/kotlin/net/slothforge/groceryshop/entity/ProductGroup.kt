package net.slothforge.groceryshop.entity

import javax.persistence.*
import javax.persistence.GenerationType.SEQUENCE

@Entity
@Table(name = "product_group")
data class ProductGroup(
        @Id
        @GeneratedValue(strategy = SEQUENCE)
        val id: Int = -1,

        val name: String = "",

        val description: String = "",

        @ManyToMany(mappedBy = "groups")
        val products: List<Product> = ArrayList()
)