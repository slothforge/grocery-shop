package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.Product;
import net.slothforge.groceryshop.entity.Unit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * For some reason IDEA does not support language injection for string concatenation in Kotlin classes
 * So sql code inside Kotlin mappers looks really ugly, therefore I've decided to use Java mappers instead.
 */

@Mapper
@Repository
public interface ProductMapper {

    //language=SQL
    String SELECT_PRODUCT = "SELECT p.id, p.name, p.unit rawUnit, p.price_per_unit price FROM product p ";
    String RETURN_PRODUCT = "RETURNING id, name, unit, price_per_unit";

    //language=SQL
    @Select(SELECT_PRODUCT)
    List<Product> listAll();

    //language=SQL
    @Select(SELECT_PRODUCT +
            "LEFT JOIN product_to_group ptg ON p.id = ptg.product_id " +
            "LEFT JOIN product_group pg ON ptg.product_group_id = pg.id " +
            "WHERE pg.id = #{productGroupId};")
    List<Product> listByProductGroupId(@Param("productGroupId") long productGroupId);

    //language=SQL
    @Select("INSERT INTO product (name, unit, price_per_unit) " +
            "VALUES (#{name}, #{unit}, #{price}) " +
            RETURN_PRODUCT + ";")
    Product insert(@Param("name") @NotNull String name,
                   @Param("unit") @NotNull Unit unit,
                   @Param("price") float price);

    //language=SQL
    @Select("UPDATE product " +
            "SET name = #{name}, unit = #{unit}, price_per_unit = #{price} " +
            "WHERE id = #{id} " +
            RETURN_PRODUCT + ";")
    Product update(@Param("id") long id,
                   @Param("name") @NotNull String name,
                   @Param("unit") @NotNull Unit unit,
                   @Param("price") float price);

    //language=SQL
    @Delete("DELETE FROM product WHERE id = #{id};")
    int delete(@Param("id") long id);
}
