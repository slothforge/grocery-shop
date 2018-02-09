package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.Role;
import net.slothforge.groceryshop.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //language=SQL
    String SELECT_USER = "SELECT " +
            "u.id, " +
            "u.password_hash passwordHash, " +
            "u.email, " +
            "u.real_name realName, " +
            "u.role rawRole " +
            "FROM \"user\" u ";

    String RETURN_USER = "RETURNING id, password_hash passwordHash, email, real_name realName, role rawRole";

    //language=SQL
    @Select(SELECT_USER)
    List<User> listAll();

    //language=SQL
    @Select(SELECT_USER +
            "WHERE u.email = #{email};")
    User findByEmail(@Param("email") @NotNull String email);

    //language=SQL
    @Select("INSERT INTO \"user\" (password_hash, email, real_name, role) " +
            "VALUES (#{passwordHash}, #{email}, #{realName}, #{role}) " +
            RETURN_USER)
    User insert(@Param("passwordHash") @NotNull String passwordHash,
                @Param("email") @NotNull String email,
                @Param("realName") @NotNull String realName,
                @Param("role") @NotNull Role role);

    //language=SQL
    @Select("UPDATE \"user\" " +
            "SET password_hash = #{passwordHash}, email = #{email}, real_name = #{realName}, role = #{role} " +
            "WHERE id = #{id} " +
            RETURN_USER)
    User update(@Param("id") long id,
                @Param("passwordHash") @NotNull String passwordHash,
                @Param("email") @NotNull String email,
                @Param("realName") @NotNull String realName,
                @Param("role") @NotNull Role role);

    //language=SQL
    @Delete("DELETE FROM \"user\" WHERE id = #{id};")
    int delete(@Param("id") long id);
}
