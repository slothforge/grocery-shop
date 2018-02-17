package net.slothforge.groceryshop.dto.create

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.entity.ProductGroup

data class ProductGroupCreateDto @JsonCreator constructor(
        @JsonProperty val name: String,
        @JsonProperty val description: String
) {
    fun toEntity(): ProductGroup = ProductGroup(name = name, description = description)
}