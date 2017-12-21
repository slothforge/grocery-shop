package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.entity.Unit

data class ProductDtoUpdate(
        val name: String = "",
        val unit: Unit = Unit.ONE,
        val price: Float = 0f,
        val groups: Set<ProductGroup> = setOf()
)