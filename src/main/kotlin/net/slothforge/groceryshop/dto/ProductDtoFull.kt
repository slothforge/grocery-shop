package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit

data class ProductDtoFull(
        val id: Long,
        val name: String,
        val unit: Unit,
        val price: Float,
        val groups: Set<ProductGroupDtoFull>
) {
    constructor(entity: Product, groups: Set<ProductGroupDtoFull>)
            : this(entity.id, entity.name, entity.unit, entity.price, groups)
}