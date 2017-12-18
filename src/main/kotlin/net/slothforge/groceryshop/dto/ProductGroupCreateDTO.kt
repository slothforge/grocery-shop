package net.slothforge.groceryshop.dto

/**
 * Spring (should be Jackson) requires empty constructor for JSON deserialization
 */

data class ProductGroupCreateDTO(
        var name: String,
        var description: String
) {
    constructor() : this("", "")
}