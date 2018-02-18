package net.slothforge.groceryshop.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.entity.ProductGroup

data class ProductGroupDto @JsonCreator constructor(
        @JsonProperty("id") val id: Int,
        @JsonProperty("name") val name: String,
        @JsonProperty("description") val description: String
) {
    constructor(entity: ProductGroup) : this(entity.id, entity.name, entity.description)

    fun toEntity() = ProductGroup(id, name, description)
}