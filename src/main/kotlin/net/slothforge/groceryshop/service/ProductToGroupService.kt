package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.mapper.ProductToGroupMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductToGroupService {

    private val logger: Logger = LoggerFactory.getLogger(ProductToGroupService::class.java)

    @Autowired
    private lateinit var mapper: ProductToGroupMapper

    fun listAllAndGroupByProductId(): Map<Long, List<ProductGroupDtoFull>> {
        return mapper.listAll()
                .groupBy({ it.productId }, { ProductGroupDtoFull(it.productGroupId, it.productGroupName, it.productGroupDescription) })
    }

    fun insertIfNotExists(productId: Long, productGroupId: Long): Int {
        logger.info("Inserting productId: {}, productGroupId: {}", productId, productGroupId)
        val result: Int = mapper.insertIfNotExists(productId, productGroupId)
        logger.info("Inserted {} rows", result)
        return result
    }

    fun deleteByProductId(productId: Long): Int {
        logger.info("Deleting rows with productId: {}", productId)
        val result: Int = mapper.deleteByProductId(productId)
        logger.info("Deleted {} rows", result)
        return result
    }
}