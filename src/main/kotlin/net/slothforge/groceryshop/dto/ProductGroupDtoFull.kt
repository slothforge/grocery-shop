package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.ProductGroup

class ProductGroupDtoFull(entity: ProductGroup) {

    val id: Long = entity.id
    val name: String = entity.name
    val description: String = entity.description
}