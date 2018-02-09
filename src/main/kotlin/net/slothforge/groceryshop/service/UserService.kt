package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.UserDto
import net.slothforge.groceryshop.dto.UserDtoFull
import net.slothforge.groceryshop.entity.User
import net.slothforge.groceryshop.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService {

    @Autowired
    private lateinit var mapper: UserMapper

    fun listAll(): List<UserDtoFull> = mapper.listAll().mapDtoFull()

    fun findByEmail(email: String): UserDtoFull =
            mapper.findByEmail(email).toDtoFull()

    fun insert(dto: UserDto): UserDtoFull =
            mapper.insert(dto.passwordHash, dto.email, dto.realName, dto.role).toDtoFull()

    fun update(id: Long, dto: UserDto): UserDtoFull =
            mapper.update(id, dto.passwordHash, dto.email, dto.realName, dto.role).toDtoFull()

    fun delete(id: Long) = mapper.delete(id) > 0

    // Extension Functions
    private fun User.toDtoFull(): UserDtoFull = UserDtoFull(this)

    private fun List<User>.mapDtoFull(): List<UserDtoFull> = map { it.toDtoFull() }

}