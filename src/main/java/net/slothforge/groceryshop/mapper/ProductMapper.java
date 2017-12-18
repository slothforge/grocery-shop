package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.Product;
import net.slothforge.groceryshop.entity.Unit;
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
public interface ProductMapper {

    String RETURN_PRODUCT = "RETURNING id, name, unit, price_per_unit";

    //language=SQL
    @Select("SELECT pr.id, pr.name, pr.unit, pr.price_per_unit price FROM product pr ;")
    List<Product> listAll();

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
