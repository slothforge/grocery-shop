package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.Application
import net.slothforge.groceryshop.dto.ProductGroupDto
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.repository.ProductGroupRepository
import net.slothforge.groceryshop.service.TestUtils.Companion.randomProductGroupDto
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
internal class ProductGroupServiceTest {

    @Autowired
    private lateinit var service: ProductGroupService
    @Autowired
    private lateinit var repository: ProductGroupRepository

    @ParameterizedTest
    @MethodSource("listOfProductGroupSource")
    fun listAllTest(productGroupList: List<ProductGroup>) {

        lateinit var expected: List<ProductGroupDto>
        try {
            expected = repository.save(productGroupList).toDtoList()

            val actual: List<ProductGroupDto> = service.listAll()

            assertThat(actual).containsAll(expected)
        } finally {
            expected.forEach { repository.delete(it.id) }
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductGroupSource")
    fun findByIdTest(productGroup: ProductGroup) {

        lateinit var expected: ProductGroupDto
        try {
            expected = repository.save(productGroup).toDto()

            val actual: ProductGroupDto = service.findById(expected.id)

            assertThat(actual).isEqualTo(expected)
        } finally {
            repository.delete(expected.id)
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductGroupSource")
    fun insertTest(productGroup: ProductGroup) {

        lateinit var expected: ProductGroupDto
        try {
            expected = service.insert(productGroup.toCreateDto())

            val actual: ProductGroupDto = repository.findOne(expected.id).toDto()

            assertThat(actual).isEqualTo(expected)
        } finally {
            repository.delete(expected.id)
        }
    }

    @ParameterizedTest
    @MethodSource("listOfProductGroupSource")
    fun insertListTest(productGroupList: List<ProductGroup>) {

        //TODO list ids
        lateinit var expected: List<ProductGroupDto>
        try {
            expected = service.insert(productGroupList.toCreateDtoList())

            val actual: List<ProductGroupDto> = repository.findAll(expected.map { it.id }).toDtoList()

            assertThat(actual).isEqualTo(expected)
        } finally {
            expected.forEach { repository.delete(it.id) }
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductGroupSource")
    fun updateTest(productGroup: ProductGroup) {

        lateinit var expected: ProductGroupDto
        try {
            val (id: Int) = repository.save(productGroup)

            expected = service.update(randomProductGroupDto(id))

            val actual: ProductGroupDto = repository.findOne(id).toDto()

            assertThat(actual).isEqualTo(expected)
        } finally {

        }
    }

    @ParameterizedTest
    @MethodSource("listOfProductGroupSource")
    fun updateListTest(productGroupList: List<ProductGroup>) {

        lateinit var expected: List<ProductGroupDto>
        try {
            val idList: List<Int> = repository.save(productGroupList).map { it.id }

            expected = service.update(idList.map { randomProductGroupDto(it) })

            val actual: List<ProductGroupDto> = repository.findAll(expected.map { it.id }).toDtoList()

            assertThat(actual).isEqualTo(expected)
        } finally {
            expected.forEach { repository.delete(it.id) }
        }
    }

    @ParameterizedTest
    @MethodSource("oneProductGroupSource")
    fun deleteByIdTest(productGroup: ProductGroup) {

        var insertedId: Int? = null
        try {
            val expected: ProductGroupDto = repository.save(productGroup).toDto()
            insertedId = expected.id

            service.delete(insertedId)
            val actual: ProductGroup? = repository.findOne(insertedId)

            assertThat(actual).isNull()
        } finally {
            if (insertedId != null && repository.findOne(insertedId) != null) {
                repository.delete(insertedId)
            }
        }
    }

    companion object {
        @JvmStatic
        private fun oneProductGroupSource(): Stream<Arguments> {
            return IntRange(0, 4)
                    .map { Arguments.of(TestUtils.randomProductGroup()) }
                    .stream()
        }

        @JvmStatic
        private fun listOfProductGroupSource(): Stream<Arguments> {
            return IntRange(0, 4)
                    .map { Arguments.of(IntRange(2, 5).map { TestUtils.randomProductGroup() }) }
                    .stream()
        }
    }

}