package net.slothforge.groceryshop.entity

data class Product(
        val id: Long,
        val name: String,
        val unit: String,
        val price: Float
)