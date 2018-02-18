package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit
import net.slothforge.groceryshop.service.toDtoList

data class ProductDto(
        val id: Int,
        val name: String,
        val unit: Unit,
        val price: Double,
        val groups: List<ProductGroupDto>
) {

    constructor(entity: Product) : this(entity.id, entity.name,entity.unit, entity.price, entity.groups.toDtoList())

    fun toEntity() = Product(id, name, unit, price, groups.map { it.toEntity() })
}