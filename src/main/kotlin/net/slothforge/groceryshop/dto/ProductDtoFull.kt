package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Product

class ProductDtoFull(entity: Product, lazyGroups: () -> List<ProductGroupDtoFull>)
    : ProductDto(entity.name, entity.unit, entity.price) {

    val id: Long = entity.id
    val groups: List<ProductGroupDtoFull> by lazy { run(lazyGroups) }
}