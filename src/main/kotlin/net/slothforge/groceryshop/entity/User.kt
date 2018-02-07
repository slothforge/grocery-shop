package net.slothforge.groceryshop.entity

data class User(
        val id: Long,
        val passwordHash: String,
        val email: String,
        val realName: String,
        val role: Role
) {
    // Used by MyBatis
    private constructor(id: Long, email: String, passwordHash: String, realName: String, rawRole: String)
            : this(id, email, passwordHash, realName, Role.valueOf(rawRole))
}

enum class Role {
    CLIENT, EMPLOYEE, MANAGER, ADMIN
}
