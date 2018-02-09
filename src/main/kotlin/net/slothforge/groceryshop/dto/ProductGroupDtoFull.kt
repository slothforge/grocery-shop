package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.ProductGroup

class ProductGroupDtoFull(entity: ProductGroup) : ProductGroupDto(entity.name, entity.description) {

    val id: Long = entity.id
}