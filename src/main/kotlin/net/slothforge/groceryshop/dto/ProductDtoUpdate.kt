package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Unit

/**
 * Jackson requires empty constructor for JSON deserialization
 */

class ProductDtoUpdate(
        val name: String = "",
        val unit: Unit = Unit.UNSPECIFIED,
        val price: Float = 0f
)