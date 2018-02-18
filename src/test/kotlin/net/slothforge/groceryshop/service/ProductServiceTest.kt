package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.Application
import net.slothforge.groceryshop.dto.ProductDto
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.repository.ProductRepository
import net.slothforge.groceryshop.service.TestUtils.Companion.randomProductCreateDto
import net.slothforge.groceryshop.service.TestUtils.Companion.randomProductDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.stream.Stream

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(Application::class)])
@TestPropertySource(locations = [("classpath:test.properties")])
internal class ProductServiceTest {

    @Autowired
    private lateinit var service: ProductService
    @Autowired
    private lateinit var repository: ProductRepository

    @ParameterizedTest
    @MethodSource("listOfProductSource")
    fun listAllTest(productList: List<Product>) {

        lateinit var expected: List<ProductDto>
        try {
            expected = repository.save(productList).toDtoList()

            val actual: List<ProductDto> = service.listAll()

            assertThat(actual).containsAll(expected)
        } finally {
            expected.forEach { repository.delete(it.id) }
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductSource")
    fun findByIdTest(product: Product) {

        lateinit var expected: ProductDto
        try {
            expected = repository.save(product).toDto()

            ///TODO handle npe
            val actual: ProductDto = service.findById(expected.id)

            assertThat(actual).isEqualTo(expected)
        } finally {
            repository.delete(expected.id)
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductSource")
    fun insertTest(product: Product) {

        lateinit var expected: ProductDto
        try {
            expected = service.insert(product.toCreateDto())

            val actual: ProductDto = repository.findOne(expected.id).toDto()

            assertThat(actual).isEqualTo(expected)
        } finally {
            repository.delete(expected.id)
        }
    }

    @ParameterizedTest
    @MethodSource("listOfProductSource")
    fun insertListTest(productList: List<Product>) {

        lateinit var expected: List<ProductDto>
        try {
            expected = service.insert(productList.toCreateDtoList())

            val actual: List<ProductDto> = repository.findAll(expected.map { it.id }).toDtoList()

            assertThat(actual).isEqualTo(expected)
        } finally {
            expected.forEach { repository.delete(it.id) }
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductSource")
    fun updateTest(product: Product) {

        lateinit var expected: ProductDto
        try {
            val (id: Int) = repository.save(product)

            expected = service.update(id, randomProductCreateDto())

            val actual: ProductDto = repository.findOne(id).toDto()

            assertThat(actual).isEqualTo(expected)
        } finally {
            repository.delete(expected.id)
        }
    }

    @ParameterizedTest
    @MethodSource("listOfProductSource")
    fun updateListTest(productList: List<Product>) {

        lateinit var expected: List<ProductDto>
        try {
            val idList: List<Int> = repository.save(productList).map { it.id }

            expected = service.update(idList.map { randomProductDto(it) })

            val actual: List<ProductDto> = repository.findAll(idList).toDtoList()

            assertThat(actual).isEqualTo(expected)
        } finally {
            expected.forEach { repository.delete(it.id) }
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductSource")
    fun deleteTest(product: Product) {

        lateinit var expected: ProductDto
        try {
            expected = repository.save(product).toDto()

            service.delete(expected.id)

            val actual: Product? = repository.findOne(expected.id)

            assertThat(actual).isNull()
        } finally {
            if (repository.findOne(expected.id) != null) {
                repository.delete(expected.id)
            }
        }
    }

    companion object {
        @JvmStatic
        private fun oneProductSource(): Stream<Arguments> {
            return IntRange(0, 4)
                    .map { Arguments.of(TestUtils.randomProduct()) }
                    .stream()
        }

        @JvmStatic
        private fun listOfProductSource(): Stream<Arguments> {
            return IntRange(0, 4)
                    .map { Arguments.of(IntRange(2, 5).map { TestUtils.randomProduct() }) }
                    .stream()
        }
    }

}