package net.slothforge.groceryshop.entity

import org.joda.time.DateTime

internal data class Supply(
        private val id: Long,
        private val date: DateTime,
        private val productId: Long,
        private val price: Float,
        private val quantity: Int
)