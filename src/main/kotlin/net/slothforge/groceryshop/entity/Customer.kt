package net.slothforge.groceryshop.entity

internal data class Customer(
        private val id: Long,
        private val firstName: String,
        private val secondName: String
)