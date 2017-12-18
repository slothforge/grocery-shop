package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.entity.Unit

data class ProductDtoFull(
        val name: String,
        val unit: Unit,
        val price: Float,
        val groups: Set<ProductGroup>
)