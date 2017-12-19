package net.slothforge.groceryshop.entity

data class Product(
        val id: Long,
        val name: String,
        private val rawUnit: String,
        val price: Float
) {
    val unit: Unit = Unit.valueOf(rawUnit)
}