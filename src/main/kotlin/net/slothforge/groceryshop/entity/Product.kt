package net.slothforge.groceryshop.entity

data class Product(
        val id: Long,
        val name: String,
        val unit: Unit,
        val price: Float
) {
    // Used by MyBatis
    constructor(id: Long, name: String, rawUnit: String, price: Float) : this(id, name, Unit.valueOf(rawUnit), price)
}

enum class Unit {
    UNSPECIFIED, ONE, KG, GR, LITER;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}
