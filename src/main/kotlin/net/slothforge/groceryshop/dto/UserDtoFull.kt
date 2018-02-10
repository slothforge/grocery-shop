package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Role
import net.slothforge.groceryshop.entity.User


data class UserDtoFull(
        val id: Long,
        val passwordHash: String,
        val email: String,
        val realName: String,
        val role: Role
) {

    constructor(user: User) : this(user.id, user.passwordHash, user.email, user.realName, user.role)
}