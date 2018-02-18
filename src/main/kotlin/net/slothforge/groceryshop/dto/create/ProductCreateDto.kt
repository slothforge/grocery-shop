package net.slothforge.groceryshop.dto.create

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.dto.ProductGroupDto
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit
import net.slothforge.groceryshop.service.toDtoList

data class ProductCreateDto @JsonCreator constructor(
        @JsonProperty("name") val name: String,
        @JsonProperty("unit") val unit: Unit,
        @JsonProperty("price") val price: Double,
        @JsonProperty("groups") val groups: List<ProductGroupDto>
) {
    constructor(entity: Product) : this(entity.name, entity.unit, entity.price, entity.groups.toDtoList())

    fun toEntity(id: Int) = Product(id, name, unit, price, groups.map { it.toEntity() })

    fun toEntity() = Product(name = name, unit = unit, price = price, groups = groups.map { it.toEntity() })
}