package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductGroupDtoUpdate
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.mapper.ProductGroupMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductGroupService {

    @Autowired
    private lateinit var mapper: ProductGroupMapper

    fun listAll(): List<ProductGroup> {
        return mapper.listAll().toList()
    }

    fun add(dto: ProductGroupDtoUpdate): ProductGroup? {
        return mapper.insert(dto.name, dto.description)
    }

    fun update(id: Long, dto: ProductGroupDtoUpdate): ProductGroup? {
        return mapper.update(id, dto.name, dto.description)
    }

    fun delete(id: Long): Boolean {
        return mapper.delete(id) > 0
    }
}