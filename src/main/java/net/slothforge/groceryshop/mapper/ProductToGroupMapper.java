package net.slothforge.groceryshop.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * For some reason IDEA does not support language injection for string concatenation in Kotlin classes
 * So sql code inside Kotlin mappers looks really ugly, therefore I've decided to use Java mappers instead.
 */

@Mapper
@Repository
public interface ProductToGroupMapper {

    //language=SQL
    @Insert("INSERT INTO product_to_group " +
            "VALUES (#{productId}, #{productGroupId});")
    int insert(@Param("productId") long productId,
               @Param("productGroupId") long productGroupId);

    //language=SQL
    @Delete("DELETE FROM product_to_group " +
            "WHERE product_id = #{productId} AND product_group_id = #{productGroupId};")
    int delete(@Param("productId") long productId,
               @Param("productGroupId") long productGroupId);

    //language=SQL
    @Delete("DELETE FROM product_to_group WHERE product_id = #{productId};")
    int deleteByProductId(@Param("productId") long productId);
}
