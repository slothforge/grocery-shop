package net.slothforge.groceryshop.entity

internal data class Product(
        private val id: Long,
        private val name: String,
        private val group: ProductGroup,
        private val price: Float
)