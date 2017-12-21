package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.ProductIdToProductGroup;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * For some reason IDEA does not support language injection for string concatenation in Kotlin classes
 * So sql code inside Kotlin mappers looks really ugly, therefore I've decided to use Java mappers instead.
 */

@Mapper
@Repository
public interface ProductToGroupMapper {

    //language=SQL
    @Select("SELECT " +
            "ptg.product_id productId, " +
            "ptg.product_group_id productGroupId," +
            "pg.name productGroupName," +
            "pg.description productGroupDescription " +
            "FROM product_to_group ptg " +
            "JOIN product_group pg ON ptg.product_group_id = pg.id;")
    List<ProductIdToProductGroup> listAll();

    //language=SQL
    @Insert("INSERT INTO product_to_group " +
            "VALUES (#{productId}, #{productGroupId}) " +
            "ON CONFLICT DO NOTHING ;")
    int insertIfNotExists(@Param("productId") long productId,
                          @Param("productGroupId") long productGroupId);

    //language=SQL
    @Delete("DELETE FROM product_to_group WHERE product_id = #{productId};")
    int deleteByProductId(@Param("productId") long productId);
}
