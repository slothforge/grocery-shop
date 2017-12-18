package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Unit

data class ProductDtoUpdate(
        val name: String,
        val unit: Unit,
        val price: Float
)