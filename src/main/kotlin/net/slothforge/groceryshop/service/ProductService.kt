package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDtoFull
import net.slothforge.groceryshop.dto.ProductDtoUpdate
import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.Unit
import net.slothforge.groceryshop.mapper.ProductMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {

    private val logger: Logger = LoggerFactory.getLogger(ProductService::class.java)

    @Autowired
    private lateinit var productGroupService: ProductGroupService
    @Autowired
    private lateinit var productToGroupService: ProductToGroupService

    @Autowired
    private lateinit var mapper: ProductMapper

    fun listAll(): List<ProductDtoFull> {
        return collectDtoFull(mapper.listAll())
    }

    fun listAll(limit: Int, offset: Int): List<ProductDtoFull> {
        return collectDtoFull(mapper.listForLimitAndOffset(limit, offset))
    }

    fun add(dtoUpdate: ProductDtoUpdate): ProductDtoFull {
        logger.info("Inserting {}", dtoUpdate)

        val insert: Product = insert(dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price)

        productToGroupService.deleteByProductId(insert.id)
        dtoUpdate.groups.forEach { productToGroupService.insertIfNotExists(insert.id, it.id) }

        val productGroupSetByProductId: Set<ProductGroupDtoFull> = productGroupService.listByProductId(insert.id)
        return ProductDtoFull(insert, productGroupSetByProductId)
    }

    fun update(id: Long, dtoUpdate: ProductDtoUpdate): ProductDtoFull {
        logger.info("Updating row with id {} on {}", id, dtoUpdate)

        val update: Product = update(id, dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price)

        productToGroupService.deleteByProductId(id)
        dtoUpdate.groups.forEach { productToGroupService.insertIfNotExists(update.id, it.id) }

        val productGroupSetByProductId: Set<ProductGroupDtoFull> = productGroupService.listByProductId(update.id)
        return ProductDtoFull(update, productGroupSetByProductId)
    }

    fun delete(id: Long): Int {
        logger.info("Deleting row with id: {}", id)
        val result: Int = mapper.delete(id)
        logger.info("Deleted {} rows", result)
        return result
    }

    private fun collectDtoFull(productList: List<Product>): List<ProductDtoFull> {
        val map = productToGroupService.listAllAndGroupByProductId()

        return productList.map { ProductDtoFull(it, map[it.id]?.toSet() ?: setOf()) }
    }

    private fun insert(name: String, unit: Unit, price: Float): Product {
        logger.info("Inserting name: {}, unit: {}, price: {}", name, unit, price)
        val result: Product = mapper.insert(name, unit, price)
        logger.info("Insert value {}", result)
        return result
    }

    private fun update(id: Long, name: String, unit: Unit, price: Float): Product {
        logger.info("Updating row with id: {}, to name: {}, unit: {}, price: {}", id, name, unit, price)
        val result: Product = mapper.update(id, name, unit, price)
        logger.info("Update value {}", result)
        return result
    }

}