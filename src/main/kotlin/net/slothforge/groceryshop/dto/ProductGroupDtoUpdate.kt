package net.slothforge.groceryshop.dto

/**
 * Jackson requires empty constructor for JSON deserialization
 */

class ProductGroupDtoUpdate(
        val name: String = "",
        val description: String = ""
)