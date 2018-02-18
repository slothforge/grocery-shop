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
            repository.findAll().toDtoList()

    fun findById(id: Int): ProductGroupDto =
            repository.findOne(id).toDto()

    fun insert(dto: ProductGroupCreateDto): ProductGroupDto =
            repository.save(dto.toEntity()).toDto()

    fun insert(dtoList: List<ProductGroupCreateDto>): List<ProductGroupDto> =
            repository.save(dtoList.map { it.toEntity() }).toDtoList()

    fun update(id: Int, dto: ProductGroupCreateDto): ProductGroupDto =
            repository.save(dto.toEntity(id)).toDto()

    fun update(dtoList: List<ProductGroupDto>): List<ProductGroupDto> =
            repository.save(dtoList.map { it.toEntity() }).toDtoList()

    fun delete(id: Int) = repository.delete(id)

}

internal fun ProductGroup?.toDto() = ProductGroupDto(checkNotNull(this))

internal fun ProductGroup?.toCreateDto() = ProductGroupCreateDto(checkNotNull(this))

internal fun Iterable<ProductGroup>.toDtoList() = map { it.toDto() }

internal fun Iterable<ProductGroup>.toCreateDtoList() = map { it.toCreateDto() }