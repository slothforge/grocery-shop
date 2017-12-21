package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.Product;
import net.slothforge.groceryshop.entity.Unit;
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
public interface ProductMapper {

    //language=SQL
    String SELECT_PRODUCT = "SELECT pr.id, pr.name, pr.unit rawUnit, pr.price_per_unit price FROM product pr ";
    String RETURN_PRODUCT = "RETURNING id, name, unit, price_per_unit";

    //language=SQL
    @Select(SELECT_PRODUCT)
    List<Product> listAll();

    //language=SQL
    @Select(SELECT_PRODUCT + "LIMIT #{limit} OFFSET #{offset};")
    List<Product> listForLimitAndOffset(@Param("limit") int limit,
                                        @Param("offset") int offset);

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
