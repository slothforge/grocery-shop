package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.dto.ProductGroupDtoUpdate
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.mapper.ProductGroupMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductGroupService {

    private val logger: Logger = LoggerFactory.getLogger(ProductGroupService::class.java)

    @Autowired
    private lateinit var mapper: ProductGroupMapper

    fun listAll(): List<ProductGroupDtoFull> {
        return mapper.listAll()
                .map { ProductGroupDtoFull(it) }
    }

    fun listAllAndGroupById(): Map<Long, List<ProductGroupDtoFull>> {
        return mapper.listAll()
                .groupBy({ it.id }, { ProductGroupDtoFull(it) })
    }

    fun listByProductId(productId: Long): Set<ProductGroupDtoFull> {
        return mapper.listByProductId(productId)
                .map { ProductGroupDtoFull(it) }.toSet()
    }

    fun add(dto: ProductGroupDtoUpdate): ProductGroupDtoFull {
        return ProductGroupDtoFull(
                insert(dto.name, dto.description)
        )
    }

    fun update(id: Long, dto: ProductGroupDtoUpdate): ProductGroupDtoFull {
        return ProductGroupDtoFull(
                update(id, dto.name, dto.description)
        )
    }

    fun delete(id: Long): Boolean {
        return mapper.delete(id) > 0
    }

    private fun insert(name: String, description: String): ProductGroup {
        logger.info("Inserting name: {}, description: {}", name, description)
        val result: ProductGroup = mapper.insert(name, description)
        logger.info("Insert {}", result)
        return result
    }

    private fun update(id: Long, name: String, description: String): ProductGroup {
        logger.info("Updating row with id: {}, to name: {}, description: {}", id, name, description)
        val result: ProductGroup = mapper.update(id, name, description)
        logger.info("Update value {}", result)
        return result
    }
}