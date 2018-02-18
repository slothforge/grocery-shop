package net.slothforge.groceryshop.service

import net.slothforge.groceryshop.dto.ProductGroupDto
import net.slothforge.groceryshop.dto.create.ProductGroupCreateDto
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.repository.ProductGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductGroupService {

    @Autowired
    private lateinit var repository: ProductGroupRepository

    fun listAll(): List<ProductGroupDto> =
            repository.findAll().mapToDtoList()

    fun findById(id: Int): ProductGroupDto =
            repository.findOne(id).toDto()

    fun insert(dto: ProductGroupCreateDto): ProductGroupDto =
            repository.save(dto.toEntity()).toDto()

    fun insert(dtoList: List<ProductGroupCreateDto>): List<ProductGroupDto> =
            repository.save(dtoList.map { it.toEntity() }).mapToDtoList()

    fun update(dto: ProductGroupDto): ProductGroupDto =
            repository.save(dto.toEntity()).toDto()

    fun update(dtoList: List<ProductGroupDto>): List<ProductGroupDto> =
            repository.save(dtoList.map { it.toEntity() }).mapToDtoList()

    fun delete(id: Int) = repository.delete(id)

    // Extension Functions
    private fun ProductGroup.toDto() = ProductGroupDto(this)

    private fun Iterable<ProductGroup>.mapToDtoList() = map { it.toDto() }

}