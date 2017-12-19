package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.Application
import net.slothforge.groceryshop.dto.ProductDtoUpdate
import net.slothforge.groceryshop.dto.ProductGroupDtoUpdate
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.entity.Unit
import net.slothforge.groceryshop.mapper.ProductGroupMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@Import(Application::class)
@TestPropertySource(locations = ["classpath:test.properties"])
@SpringBootTest(classes = [(ProductGroupService::class), (ProductGroupMapper::class)])
class DummyServicesTest {

    @Autowired
    private lateinit var productGroupService: ProductGroupService
    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun productGroupServiceDummyTest() {
        val dtoUpdate = ProductGroupDtoUpdate("test", "test")

        val inserted: ProductGroup? = productGroupService.add(dtoUpdate)
        productGroupService.update(inserted!!.id, dtoUpdate)
        productGroupService.listAll()
        productGroupService.delete(inserted.id)
    }

    @Test
    internal fun productServiceDummyTest() {
        val dtoUpdate = ProductDtoUpdate("test", Unit.ONE, 0f)

        val inserted: Product? = productService.add(dtoUpdate)
        productService.update(inserted!!.id, dtoUpdate)
        productService.listAll()
        productService.delete(inserted.id)

        println(productService.listAllFullDto())
    }
}