package net.slothforge.groceryshop.mapper;

import net.slothforge.groceryshop.entity.ProductToGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductToGroupMapper {

    //language=SQL
    @Select("SELECT ptg.product_id productId, ptg.product_group_id productGroupId FROM product_to_group ptg;")
    List<ProductToGroup> listAll();
}
