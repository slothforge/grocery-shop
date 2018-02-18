package net.slothforge.groceryshop.dto.create

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.dto.ProductGroupDto
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit
import net.slothforge.groceryshop.service.toDtoList

data class ProductCreateDto @JsonCreator constructor(
        @JsonProperty val name: String,
        @JsonProperty val unit: Unit,
        @JsonProperty val price: Double,
        @JsonProperty val groups: List<ProductGroupDto>
) {

    constructor(entity: Product) : this(entity.name, entity.unit, entity.price, entity.groups.toDtoList())

    fun toEntity() = Product(name = name, unit = unit, price = price, groups = groups.map { it.toEntity() })
}