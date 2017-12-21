package net.slothforge.groceryshop.dto

/**
 * Spring (should be Jackson) requires empty constructor for JSON deserialization
 */

data class ProductGroupDtoUpdate(
        val name: String = "",
        val description: String = ""
)