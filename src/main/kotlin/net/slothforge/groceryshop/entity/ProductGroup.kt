package net.slothforge.groceryshop.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.persistence.GenerationType.AUTO

@Entity
@Table(name = "product_group")
data class ProductGroup(
        @Id
        @GeneratedValue(strategy = AUTO)
        var id: Int = -1,

        var name: String = "",

        var description: String = "",

        @JsonIgnore
        @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
        var products: List<Product> = ArrayList()
)