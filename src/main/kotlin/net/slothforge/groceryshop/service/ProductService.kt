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
            repository.findAll().mapToDtoList()

    fun findById(id: Int): ProductDto =
            repository.findOne(id).toDto()

    fun insert(dto: ProductCreateDto): ProductDto =
            repository.save(dto.toEntity()).toDto()

    fun insert(dtoList: List<ProductCreateDto>): List<ProductDto> =
            repository.save(dtoList.map { it.toEntity() }).mapToDtoList()

    fun update(dto: ProductDto): ProductDto =
            repository.save(dto.toEntity()).toDto()

    fun update(dtoList: List<ProductDto>): List<ProductDto> =
            repository.save(dtoList.map { it.toEntity() }).mapToDtoList()

    fun delete(id: Int) = repository.delete(id)

    fun delete(dto: ProductDto) =
            repository.delete(dto.toEntity())

    fun delete(dtoList: List<ProductDto>) =
            repository.delete(dtoList.map { it.toEntity() })

    // Extension Functions
    private fun Product.toDto() = ProductDto(this)

    private fun Iterable<Product>.mapToDtoList() = map { it.toDto() }

}