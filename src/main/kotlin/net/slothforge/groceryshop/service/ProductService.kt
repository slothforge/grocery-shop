package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductDto
import net.slothforge.groceryshop.dto.create.ProductCreateDto
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    private lateinit var repository: ProductRepository

    fun listAll(): List<ProductDto> =
            repository.findAll().toDtoList()

    fun findById(id: Int): ProductDto =
            repository.findOne(id).toDto()

    fun insert(dto: ProductCreateDto): ProductDto =
            repository.save(dto.toEntity()).toDto()

    fun insert(dtoList: List<ProductCreateDto>): List<ProductDto> =
            repository.save(dtoList.map { it.toEntity() }).toDtoList()

    fun update(id: Int, dto: ProductCreateDto): ProductDto =
            repository.save(dto.toEntity(id)).toDto()

    fun update(dtoList: List<ProductDto>): List<ProductDto> =
            repository.save(dtoList.map { it.toEntity() }).toDtoList()

    fun delete(id: Int) = repository.delete(id)
}

internal fun Product?.toDto() = ProductDto(checkNotNull(this))

internal fun Product?.toCreateDto() = ProductCreateDto(checkNotNull(this))

internal fun Iterable<Product>.toDtoList() = map { it.toDto() }

internal fun Iterable<Product>.toCreateDtoList() = map { it.toCreateDto() }
