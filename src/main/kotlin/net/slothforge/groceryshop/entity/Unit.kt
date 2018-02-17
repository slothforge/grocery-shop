package net.slothforge.groceryshop.entity

enum class Unit {
    UNSPECIFIED, ONE, KG, GR, LITER;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }}