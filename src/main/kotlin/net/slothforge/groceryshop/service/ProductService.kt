package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDto
import net.slothforge.groceryshop.dto.ProductDtoFull
import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.mapper.ProductMapper
import net.slothforge.groceryshop.mapper.ProductToGroupMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {

    @Autowired
    private lateinit var productGroupService: ProductGroupService

    @Autowired
    private lateinit var productMapper: ProductMapper
    @Autowired
    private lateinit var productToGroupMapper: ProductToGroupMapper

    fun listAll(): List<ProductDtoFull> = productMapper.listAll().mapToDtoFull()

    fun listByProductGroup(dtoFull: ProductDtoFull): List<ProductDtoFull> =
            productMapper.listByProductGroupId(dtoFull.id).mapToDtoFull()

    fun findById(id: Long): ProductDtoFull = productMapper.findById(id).toDtoFull()

    fun insert(dto: ProductDto,
               groups: List<ProductGroupDtoFull> = listOf()): ProductDtoFull {

        val inserted: Product = productMapper.insert(dto.name, dto.unit, dto.price)

        groups.forEach {
            productToGroupMapper.insert(inserted.id, it.id)
        }

        return inserted.toDtoFull()
    }

    fun update(id: Long,
               dto: ProductDto,
               groups: List<ProductGroupDtoFull> = listOf()): ProductDtoFull {

        val updated: Product = productMapper.update(id, dto.name, dto.unit, dto.price)

        val relatedProductGroupList = productGroupService.listByProductId(updated.id)
        val addedProductGroupList = relatedProductGroupList - groups
        val removedProductGroupList = groups - relatedProductGroupList

        addedProductGroupList.forEach { productToGroupMapper.insert(updated.id, it.id) }
        removedProductGroupList.forEach { productToGroupMapper.delete(updated.id, it.id) }

        return updated.toDtoFull()
    }

    fun delete(id: Long): Boolean {
        productToGroupMapper.deleteByProductId(id)

        return productMapper.delete(id) > 0
    }

    // Extension Functions
    private fun Product.toDtoFull(): ProductDtoFull =
            ProductDtoFull(this, { productGroupService.listByProductId(id) })

    private fun List<Product>.mapToDtoFull(): List<ProductDtoFull> = map { it.toDtoFull() }
}