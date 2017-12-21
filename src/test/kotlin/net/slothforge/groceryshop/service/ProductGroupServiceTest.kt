//package net.slothforge.groceryshop.service
//
//import net.slothforge.groceryshop.Application
//import net.slothforge.groceryshop.entity.ProductGroup
//import net.slothforge.groceryshop.mapper.ProductGroupMapper
//import org.apache.commons.lang3.RandomStringUtils
//import org.assertj.core.api.Assertions.assertThat
//import org.assertj.core.api.SoftAssertions
//import org.junit.jupiter.api.extension.ExtendWith
//import org.junit.jupiter.params.ParameterizedTest
//import org.junit.jupiter.params.provider.Arguments
//import org.junit.jupiter.params.provider.MethodSource
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.context.annotation.Import
//import org.springframework.test.context.TestPropertySource
//import org.springframework.test.context.junit.jupiter.SpringExtension
//
//@ExtendWith(SpringExtension::class)
//@Import(Application::class)
//@TestPropertySource(locations = ["classpath:test.properties"])
//@SpringBootTest(classes = [(ProductGroupService::class), (ProductGroupMapper::class)])
//class ProductGroupServiceTest {
//TODO for better times
//    @Autowired
//    private lateinit var productGroupService: ProductGroupService
//
//    @ParameterizedTest
//    @MethodSource(value = ["fewProductGroup"])
//    fun listAllTest(parameterList: List<ProductGroup>) {
//        val soft = SoftAssertions()
//
//        val insertedList: List<ProductGroup?> =
//                parameterList.map { productGroup -> productGroupService.add(productGroup.name, productGroup.description) }
//
//        try {
//            val actual: List<ProductGroup> = productGroupService.listAll()
//
//            soft.assertThat(actual)
//                    .isNotNull.isNotEmpty
//
//            parameterList.forEach { parameterPG ->
//                run {
//                    soft.assertThat(actual.any { actualGP -> fieldsExceptIdAreEquals(actualGP, parameterPG) }).isTrue
//                }
//            }
//
//            insertedList.forEach { inserted -> soft.assertThat(inserted).isNotNull }
//            insertedList.forEach { insertedPG ->
//                run {
//                    soft.assertThat(actual.any { actualGP -> insertedPG == actualGP })
//                }
//            }
//
//            soft.assertAll()
//        } finally {
//            insertedList.forEach { productGroup -> productGroup?.id?.let { productGroupService.delete(productGroup.id) } }
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource(value = ["oneProductGroup"])
//    fun findByIdTest(parameter: ProductGroup) {
//        val soft = SoftAssertions()
//
//        val inserted: ProductGroup? = productGroupService.add(parameter.name, parameter.description)
//
//        try {
//            assertInsertedCorrectly(inserted, parameter)
//
//            val actual: ProductGroup? = productGroupService.findById(inserted!!.id)
//
//            soft.assertThat(actual)
//                    .isNotNull
//                    .isEqualTo(inserted)
//
//            soft.assertAll()
//        } finally {
//            inserted?.id?.let { productGroupService.delete(inserted.id) }
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource(value = ["oneProductGroup"])
//    fun addTest(parameter: ProductGroup) {
//
//        val inserted: ProductGroup? = productGroupService.add(parameter.name, parameter.description)
//
//        try {
//            assertInsertedCorrectly(inserted, parameter)
//
//            val actual: ProductGroup? = productGroupService.findById(inserted!!.id)
//
//            assertThat(actual)
//                    .isNotNull()
//                    .isEqualTo(inserted)
//
//        } finally {
//            inserted?.id?.let { productGroupService.delete(inserted.id) }
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource(value = ["oneProductGroup"])
//    fun updateTest(parameter: ProductGroup) {
//        val soft = SoftAssertions()
//
//        val inserted: ProductGroup? = productGroupService.add(parameter.name, parameter.description)
//
//        try {
//            assertInsertedCorrectly(inserted, parameter)
//
//            val expectedName = RandomStringUtils.random(5)
//            val expectedDescription = RandomStringUtils.random(10)
//            val updated: ProductGroup? = productGroupService.update(inserted!!.id, expectedName, expectedDescription)
//
//            val actual: ProductGroup? = productGroupService.findById(inserted.id)
//
//            soft.assertThat(actual)
//                    .isEqualTo(updated)
//
//            soft.assertThat(actual).isNotNull
//            soft.assertThat(actual?.name)
//                    .isNotNull
//                    .isEqualTo(expectedName)
//            soft.assertThat(actual?.description)
//                    .isNotNull
//                    .isEqualTo(expectedDescription)
//
//            soft.assertAll()
//        } finally {
//            inserted?.id?.let { productGroupService.delete(inserted.id) }
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource(value = ["oneProductGroup"])
//    fun deleteTest(parameter: ProductGroup) {
//
//        val inserted: ProductGroup? = productGroupService.add(parameter.name, parameter.description)
//
//        assertThat(inserted).isNotNull()
//        assertThat(inserted!!.id).isNotNull()
//
//        productGroupService.delete(inserted.id)
//
//        assertThat(productGroupService.findById(inserted.id))
//                .isNull()
//    }
//
//    companion object {
//
//        // Parameter sources
//        @JvmStatic
//        fun oneProductGroup() = listOf<Arguments>(
//                Arguments.of(randomProductGroup()),
//                Arguments.of(randomProductGroup()),
//                Arguments.of(randomProductGroup())
//        )
//
//        @JvmStatic
//        fun fewProductGroup() = listOf<Arguments>(
//                Arguments.of(listOf(randomProductGroup(), randomProductGroup(), randomProductGroup())),
//                Arguments.of(listOf(randomProductGroup(), randomProductGroup())),
//                Arguments.of(listOf(randomProductGroup()))
//        )
//
//        private fun randomProductGroup(): ProductGroup {
//            return ProductGroup(
//                    -1L,
//                    RandomStringUtils.randomAlphabetic(5),
//                    RandomStringUtils.randomAlphabetic(10)
//            )
//        }
//
//        // Assertions
//        private fun assertInsertedCorrectly(inserted: ProductGroup?, parameter: ProductGroup) {
//            assertThat(inserted).isNotNull()
//            assertThat(inserted?.id).isNotNull()
//            assertThat(fieldsExceptIdAreEquals(parameter, inserted!!)).isTrue()
//        }
//
//        private fun fieldsExceptIdAreEquals(pg1: ProductGroup, pg2: ProductGroup): Boolean {
//            return pg1.name == pg2.name && pg1.description == pg2.description
//        }
//    }
//
//}