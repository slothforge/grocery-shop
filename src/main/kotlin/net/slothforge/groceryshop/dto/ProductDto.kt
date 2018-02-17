package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit

data class ProductDto(
        val id: Int,
        val name: String,
        val unit: Unit,
        val price: Double,
        val groups: List<ProductGroupDto>
) {

    constructor(entity: Product) : this(
            entity.id,
            entity.name,
            entity.unit,
            entity.price,
            entity.groups.map { ProductGroupDto(it) }
    )

    fun toEntity() = Product(id, name, unit, price, groups.map { it.toEntity() })
}