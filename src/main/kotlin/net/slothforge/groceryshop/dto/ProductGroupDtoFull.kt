package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.ProductGroup

class ProductGroupDtoFull(
        val id: Long,
        val name: String,
        val description: String
) {
    constructor(entity: ProductGroup) : this(entity.id, entity.name, entity.description)
}