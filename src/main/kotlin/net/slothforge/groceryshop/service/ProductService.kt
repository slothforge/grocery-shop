package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDtoFull
import net.slothforge.groceryshop.dto.ProductDtoUpdate
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.entity.ProductToGroup
import net.slothforge.groceryshop.mapper.ProductGroupMapper
import net.slothforge.groceryshop.mapper.ProductMapper
import net.slothforge.groceryshop.mapper.ProductToGroupMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {

    @Autowired
    private lateinit var productMapper: ProductMapper
    @Autowired
    private lateinit var productGroupMapper: ProductGroupMapper
    @Autowired
    private lateinit var productToGroupMapper: ProductToGroupMapper

    fun listAll(): List<Product> {
        return productMapper.listAll().toList()
    }

    fun listAllFullDto(): List<ProductDtoFull> {

        val groupIdToGroupMap: Map<Long, ProductGroup> = productGroupMapper.listAll()
                .map { it.id to it }.toMap()

        val productIdToGroupIdListMap: MutableMap<Long, MutableList<Long>> = mutableMapOf()

        for (productToGroup: ProductToGroup in productToGroupMapper.listAll()) {
            productIdToGroupIdListMap.computeIfAbsent(productToGroup.productId, { mutableListOf() })
            productIdToGroupIdListMap[productToGroup.productId]!!.add(productToGroup.productGroupId)
        }

        return listAll()
                .map { product ->
                    run {
                        val productGroupSet: Set<ProductGroup> = productIdToGroupIdListMap.getOrDefault(product.id, mutableListOf())
                                .mapNotNull { groupIdToGroupMap[it] }.toSet()

                        ProductDtoFull(product.name, product.unit, product.price, productGroupSet)
                    }
                }
                .toList()
    }

    fun add(dtoUpdate: ProductDtoUpdate): Product? {
        return productMapper.insert(dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price)
    }

    fun update(id: Long, dtoUpdate: ProductDtoUpdate): Product? {
        return productMapper.update(id, dtoUpdate.name, dtoUpdate.unit, dtoUpdate.price)
    }

    fun delete(id: Long): Boolean {
        return productMapper.delete(id) > 0
    }
}