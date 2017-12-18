package net.slothforge.groceryshop.entity

import org.joda.time.DateTime

internal data class Payment(
        private val id: Long,
        private val customerId: Long,
        private val date: DateTime
)