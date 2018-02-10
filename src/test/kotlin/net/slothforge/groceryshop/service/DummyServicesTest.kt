package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.Application
import net.slothforge.groceryshop.dto.*
import net.slothforge.groceryshop.entity.Role
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
@SpringBootTest(classes = [(ProductGroupService::class), (ProductGroupMapper::class), (UserService::class)])
class DummyServicesTest {

    @Autowired
    private lateinit var productGroupService: ProductGroupService
    @Autowired
    private lateinit var productService: ProductService
    @Autowired
    private lateinit var userService: UserService

    @Test
    fun productGroupServiceDummyTest() {
        val dto = ProductGroupDto("test", "test")

        val inserted = productGroupService.insert(dto)
        println(productGroupService.listAll())
        println(productGroupService.findById(inserted.id))
        productGroupService.update(inserted.id, dto)
        productGroupService.delete(inserted.id)
    }

    @Test
    fun productServiceDummyTest() {
        val dto = ProductDto("test", Unit.ONE, 0f)

        val inserted = productService.insert(dto)
        println(productService.listAll())
        println(productService.findById(inserted.id))
        productService.update(inserted.id, dto)
        productService.delete(inserted.id)
    }

    @Test
    fun combinedProductServiceAndProductGroupServiceTest() {
        val productDto = ProductDto("test", Unit.ONE, 0f)
        val productGroupDto = ProductGroupDto("test", "test")

        val productGroupDto1 = productGroupService.insert(ProductGroupDto("test1", "test1"))

        val insertedProductGroup = productGroupService.insert(productGroupDto)
        val insertedProduct = productService.insert(productDto, listOf(insertedProductGroup, productGroupDto1))

        println(productService.listByProductGroup(insertedProductGroup.id))
        println(productGroupService.listByProductId(insertedProduct.id))

        val productGroupDto2 = productGroupService.insert(ProductGroupDto("test2", "test2"))
        productService.update(insertedProduct.id, productDto, listOf(productGroupDto1, productGroupDto2))

        println(productGroupService.listByProductId(insertedProduct.id))

        productService.delete(insertedProduct.id)
        productGroupService.delete(insertedProductGroup.id)
        productGroupService.delete(productGroupDto1.id)
        productGroupService.delete(productGroupDto2.id)
    }

    @Test
    internal fun userServiceDummyTest() {
        val dto = UserDto("123", "mail@test.com", "Test Test", Role.CLIENT)

        val inserted = userService.insert(dto)
        println(userService.listAll())
        println(userService.findByEmail(dto.email))
        userService.update(inserted.id, dto)
        userService.delete(inserted.id)
    }
}