package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.ProductGroup

data class ProductGroupDto(
        val id: Int,
        val name: String,
        val description: String
) {
    constructor(entity: ProductGroup) : this(entity.id, entity.name, entity.description)

    fun toEntity() = ProductGroup(id, name, description)
}