package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.dto.ProductGroupDto
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

    fun findById(id: Long): ProductGroupDtoFull =
            mapper.findById(id).toDtoFull()

    fun insert(dto: ProductGroupDto): ProductGroupDtoFull =
            mapper.insert(dto.name, dto.description).toDtoFull()

    fun update(id: Long, dto: ProductGroupDto): ProductGroupDtoFull =
            mapper.update(id, dto.name, dto.description).toDtoFull()

    fun delete(id: Long): Boolean = mapper.delete(id) > 0

    // Extension Functions
    private fun ProductGroup.toDtoFull() = ProductGroupDtoFull(this)

    private fun List<ProductGroup>.mapToDtoFull() = map { it.toDtoFull() }
}