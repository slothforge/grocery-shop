package net.slothforge.groceryshop.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.entity.Unit

open class ProductDto @JsonCreator constructor(
        @JsonProperty val name: String,
        @JsonProperty val unit: Unit,
        @JsonProperty val price: Float
)