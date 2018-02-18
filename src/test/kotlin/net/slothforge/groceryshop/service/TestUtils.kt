package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDto
import net.slothforge.groceryshop.dto.ProductGroupDto
import net.slothforge.groceryshop.dto.create.ProductCreateDto
import net.slothforge.groceryshop.dto.create.ProductGroupCreateDto
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.entity.Unit
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomUtils.nextDouble
import org.apache.commons.lang3.RandomUtils.nextInt

internal class TestUtils {
    companion object {

        // Generate random entities
        fun randomProduct(): Product {
            return Product(
                    name = randomAlphabetic(10),
                    unit = Unit.values()[nextInt(0, Unit.values().size)],
                    price = nextDouble(),
                    groups = IntRange(0, nextInt(1, 3)).map { randomProductGroup() }
            )
        }

        fun randomProductDto(id: Int): ProductDto {
            return ProductDto(
                    id,
                    randomAlphabetic(10),
                    Unit.values()[nextInt(0, Unit.values().size)],
                    nextDouble(),
                    IntRange(0, nextInt(1, 3)).map { randomProductGroup().toDto() }
            )
        }

        fun randomProductGroup(): ProductGroup {
            return ProductGroup(name = randomAlphabetic(5), description = randomAlphabetic(10))
        }

        fun randomProductGroupDto(id: Int): ProductGroupDto {
            return ProductGroupDto(id, randomAlphabetic(5), randomAlphabetic(10))
        }
    }
}

internal fun Product.toDto() = ProductDto(this)

internal fun Product.toCreateDto() = ProductCreateDto(name, unit, price, groups.map { it.toDto() })

internal fun ProductGroup.toDto() = ProductGroupDto(this)

internal fun ProductGroup.toCreateDto() = ProductGroupCreateDto(name, description)

// TODO any way to use refiled type?
internal fun Iterable<Product>.toProductDtoList() = map { it.toDto() }

internal fun Iterable<Product>.toProductCreateDtoList() = map { it.toCreateDto() }

internal fun Iterable<ProductGroup>.toProductGroupDtoList() = map { it.toDto() }

internal fun Iterable<ProductGroup>.toProductGroupCreateDtoList() = map { it.toCreateDto() }