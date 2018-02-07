package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.Role
import net.slothforge.groceryshop.entity.User

class UserDtoFull(user: User) {

    val id: Long = user.id
    val passwordHash: String = user.passwordHash
    val email: String = user.email
    val realName: String = user.realName
    val role: Role = user.role
}