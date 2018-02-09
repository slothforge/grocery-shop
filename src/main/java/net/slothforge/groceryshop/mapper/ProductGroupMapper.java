package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.ProductGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * For some reason IDEA does not support language injection for string concatenation in Kotlin classes
 * So sql code inside Kotlin mappers looks really ugly, therefore I've decided to use Java mappers instead.
 */

@Mapper
@Repository
public interface ProductGroupMapper {

    //language=SQL
    String SELECT_PRODUCT_GROUP = "SELECT " +
            "pg.id, " +
            "pg.name, " +
            "pg.description " +
            "FROM product_group pg ";

    String RETURN_PRODUCT_GROUP = "RETURNING id, name, description";

    //language=SQL
    @Select(SELECT_PRODUCT_GROUP)
    List<ProductGroup> listAll();

    //language=SQL
    @Select(SELECT_PRODUCT_GROUP +
            "JOIN product_to_group ptg ON pg.id = ptg.product_group_id " +
            "WHERE ptg.product_id = #{productId};")
    List<ProductGroup> listByProductId(@Param("productId") long productId);

    //language=SQL
    @Select(SELECT_PRODUCT_GROUP +
            "WHERE pg.id = #{id};")
    ProductGroup findById(@Param("id") long id);

    //language=SQL
    @Select("INSERT INTO product_group (name, description) " +
            "VALUES (#{name}, #{description}) " +
            RETURN_PRODUCT_GROUP)
    ProductGroup insert(@Param("name") @NotNull String name,
                        @Param("description") @NotNull String description);

    //language=SQL
    @Select("UPDATE product_group " +
            "SET name = #{name}, description = #{description} " +
            "WHERE id = #{id} " +
            RETURN_PRODUCT_GROUP)
    ProductGroup update(@Param("id") long id,
                        @Param("name") @NotNull String name,
                        @Param("description") @NotNull String description);

    //language=SQL
    @Delete("DELETE FROM product_group WHERE id = ${id};")
    int delete(@Param("id") long id);
}
