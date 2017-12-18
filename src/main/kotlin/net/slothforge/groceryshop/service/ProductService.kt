package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDtoUpdate
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.mapper.ProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {

    @Autowired
    private lateinit var mapper: ProductMapper

    fun listAll(): List<Product> {
        return mapper.listAll().toList()
    }

    fun add(dtoUpdate: ProductDtoUpdate): Product? {
        return mapper.insert(dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price)
    }

    fun update(id: Long, dtoUpdate: ProductDtoUpdate): Product? {
        return mapper.update(id, dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price)
    }

    fun delete(id: Long): Boolean {
        return mapper.delete(id) > 0
    }
}