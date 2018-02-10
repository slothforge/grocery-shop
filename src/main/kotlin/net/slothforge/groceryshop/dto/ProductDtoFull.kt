package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit

data class ProductDtoFull(
        val id: Long,
        val name: String,
        val unit: Unit,
        val price: Float,
        private val lazyGroups: () -> List<ProductGroupDtoFull>
) {
    val groups: List<ProductGroupDtoFull> by lazy(lazyGroups)

    constructor(entity: Product, lazyGroups: () -> List<ProductGroupDtoFull>):
            this(entity.id, entity.name, entity.unit, entity.price, lazyGroups)
}