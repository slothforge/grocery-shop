package net.slothforge.groceryshop.entity

data class ProductIdToProductGroup (
        val productId: Long,
        val productGroupId: Long,
        val productGroupName: String,
        val productGroupDescription: String
)