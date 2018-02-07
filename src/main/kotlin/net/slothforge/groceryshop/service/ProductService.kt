package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDtoFull
import net.slothforge.groceryshop.dto.ProductDtoUpdate
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.mapper.ProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {

    @Autowired
    private lateinit var productGroupService: ProductGroupService

    @Autowired
    private lateinit var mapper: ProductMapper

    fun listAll(): List<ProductDtoFull> = mapper.listAll().mapToDtoFull()

    fun listByProductGroup(dtoFull: ProductDtoFull): List<ProductDtoFull> =
            mapper.listByProductGroupId(dtoFull.id).mapToDtoFull()

    fun insert(dtoUpdate: ProductDtoUpdate): ProductDtoFull =
            mapper.insert(dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price).toDtoFull()

    fun update(id: Long, dtoUpdate: ProductDtoUpdate): ProductDtoFull =
            mapper.update(id, dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price).toDtoFull()

    fun delete(id: Long) = mapper.delete(id) > 0

    // Extension Functions
    private fun Product.toDtoFull(): ProductDtoFull =
            ProductDtoFull(this, productGroupService.listByProductId(id))

    private fun List<Product>.mapToDtoFull(): List<ProductDtoFull> = map { it.toDtoFull() }
}