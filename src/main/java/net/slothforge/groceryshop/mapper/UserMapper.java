package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

    //language=SQL
    @Select(SELECT_USER)
    List<User> listAll();

    //language=SQL
    @Select(SELECT_USER +
            "WHERE u.email = #{email};")
    User findByEmail(@Param("email") String email);

}
