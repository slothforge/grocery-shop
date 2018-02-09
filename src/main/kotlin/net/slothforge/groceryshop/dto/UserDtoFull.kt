package net.slothforge.groceryshop.dto

import net.slothforge.groceryshop.entity.User

class UserDtoFull(user: User) : UserDto(user.passwordHash, user.email, user.realName, user.role) {

    val id: Long = user.id
}