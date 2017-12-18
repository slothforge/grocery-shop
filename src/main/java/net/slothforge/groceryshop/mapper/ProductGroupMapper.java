package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.ProductGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * For some reason IDEA do not support language injection on string concatenation for mappers written on Kotlin
 * So sql code inside Kotlin mappers looks really ugly, therefore I've decided to use Java mappers instead.
 */

@Mapper
public interface ProductGroupMapper {

    String RETURN_PRODUCT_GROUP = "RETURNING id, name, description";

    //language=SQL
    @Select("SELECT pg.id, pg.name, pg.description FROM product_group pg;")
    List<ProductGroup> listAll();

    //language=SQL
    @Select("INSERT INTO product_group (name, description) " +
            "VALUES (#{name}, #{description}) " +
            RETURN_PRODUCT_GROUP + ";")
    ProductGroup insert(@Param("name") @NotNull String name,
                        @Param("description") @NotNull String description);

    //language=SQL
    @Select("UPDATE product_group " +
            "SET name = #{name}, description = #{description} " +
            "WHERE id = #{id} " +
            RETURN_PRODUCT_GROUP + ";")
    ProductGroup update(@Param("id") long id,
                        @Param("name") @NotNull String name,
                        @Param("description") @NotNull String description);

    //language=SQL
    @Delete("DELETE FROM product_group WHERE id = ${id};")
    int delete(@Param("id") long id);
}
