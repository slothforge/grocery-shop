package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.ProductGroup;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Mapper written on Kotlin for some reason idea do not support language injection for string concatenation
 * So sql code looks really ugly, therefore I decided to use Java for mappers instead.
 */

@Mapper
public interface ProductGroupMapper {

    String SELECT_PRODUCT_GROUP = "SELECT pg.id, pg.name, pg.description FROM product_group pg ";

    @Select(SELECT_PRODUCT_GROUP + ";")
    List<ProductGroup> listAll();

    //language=SQL
    @Select(SELECT_PRODUCT_GROUP +
            "WHERE pg.id = #{id};")
    ProductGroup findById(@Param("id") long id);

    //language=SQL
    @Select("INSERT INTO product_group (name, description) " +
            "VALUES (#{name}, #{description}) " +
            "RETURNING id, name, description;")
    ProductGroup insert(@Param("name") @NotNull String name,
                        @Param("description") @NotNull String description);

    //language=SQL
    @Select("UPDATE product_group " +
            "SET name = #{name}, description = #{description} " +
            "WHERE id = #{id} " +
            "RETURNING id, name, description;")
    ProductGroup update(@Param("id") @NotNull long id,
                        @Param("name") @NotNull String name,
                        @Param("description") @NotNull String description);

    //language=SQL
    @Delete("DELETE FROM product_group WHERE id = ${id};")
    int delete(@Param("id") long id);
}
