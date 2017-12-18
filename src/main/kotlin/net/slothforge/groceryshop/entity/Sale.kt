package net.slothforge.groceryshop.entity

internal data class Sale(
        private val paymentId: Long,
        private val productId: Long,
        private val price: Float,
        private val quantity: Int
)