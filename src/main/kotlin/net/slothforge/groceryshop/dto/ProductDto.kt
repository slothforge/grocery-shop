package net.slothforge.groceryshop.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit
import net.slothforge.groceryshop.service.toDtoList

data class ProductDto @JsonCreator constructor(
        @JsonProperty("id") val id: Int,
        @JsonProperty("name") val name: String,
        @JsonProperty("unit") val unit: Unit,
        @JsonProperty("price") val price: Double,
        @JsonProperty("groups") val groups: List<ProductGroupDto>
) {
    constructor(entity: Product) : this(entity.id, entity.name,entity.unit, entity.price, entity.groups.toDtoList())

    fun toEntity() = Product(id, name, unit, price, groups.map { it.toEntity() })
}