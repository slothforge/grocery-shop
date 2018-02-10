package net.slothforge.groceryshop.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import net.slothforge.groceryshop.entity.Role

data class UserDto @JsonCreator constructor(
        @JsonProperty val passwordHash: String,
        @JsonProperty val email: String,
        @JsonProperty val realName: String,
        @JsonProperty val role: Role
)