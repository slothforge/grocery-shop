package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit

class ProductDtoFull(entity: Product, val groups: List<ProductGroupDtoFull>) {

    val id: Long = entity.id
    val name: String = entity.name
    val unit: Unit = entity.unit
    val price: Float = entity.price
}