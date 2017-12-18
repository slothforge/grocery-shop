package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.mapper.ProductGroupMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductGroupService {

    @Autowired
    lateinit var mapper: ProductGroupMapper

    internal fun listAll(): List<ProductGroup> {
        return mapper.listAll().toList()
    }

    internal fun findById(id: Long): ProductGroup? {
        return mapper.findById(id)
    }

    internal fun add(name: String, description: String): ProductGroup? {
        return mapper.insert(name, description)
    }

    internal fun update(id: Long, name: String, description: String): ProductGroup? {
        return mapper.update(id, name, description)
    }

    internal fun delete(id: Long): Int {
        return mapper.delete(id)
    }
}