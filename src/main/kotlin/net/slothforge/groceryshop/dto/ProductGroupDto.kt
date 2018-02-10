package net.slothforge.groceryshop.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductGroupDto @JsonCreator constructor(
        @JsonProperty val name: String,
        @JsonProperty val description: String
)