package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.dto.ProductGroupDtoUpdate
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.mapper.ProductGroupMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductGroupService {

    @Autowired
    private lateinit var mapper: ProductGroupMapper

    fun listAll(): List<ProductGroupDtoFull> = mapper.listAll().mapToDtoFull()

    fun listByProductId(productId: Long): List<ProductGroupDtoFull> =
            mapper.listByProductId(productId).mapToDtoFull()

    fun insert(dto: ProductGroupDtoUpdate): ProductGroupDtoFull =
            mapper.insert(dto.name, dto.description).toDtoFull()

    fun update(id: Long, dto: ProductGroupDtoUpdate): ProductGroupDtoFull =
            mapper.update(id, dto.name, dto.description).toDtoFull()

    fun delete(id: Long): Boolean = mapper.delete(id) > 0

    // Extension Functions
    private fun ProductGroup.toDtoFull() = ProductGroupDtoFull(this)

    private fun List<ProductGroup>.mapToDtoFull() = map { it.toDtoFull() }
}