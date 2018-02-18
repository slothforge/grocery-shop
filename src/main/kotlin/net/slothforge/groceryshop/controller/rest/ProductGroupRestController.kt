package net.slothforge.groceryshop.controller.rest

import net.slothforge.groceryshop.dto.ProductGroupDto
import net.slothforge.groceryshop.dto.create.ProductGroupCreateDto
import net.slothforge.groceryshop.service.ProductGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/product-group")
class ProductGroupRestController {

    @Autowired
    private lateinit var service: ProductGroupService

    @RequestMapping("/all", method = [RequestMethod.GET])
    fun listAll(): List<ProductGroupDto> {
        return service.listAll()
    }

    @RequestMapping("/{id}", method = [RequestMethod.GET])
    fun findById(@PathVariable id: Int): ProductGroupDto {
        return service.findById(id)
    }


    @RequestMapping("/add", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody dtoList: List<ProductGroupCreateDto>): List<ProductGroupDto> {
        return service.insert(dtoList)
    }

    @RequestMapping("/{id}", method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Int, @RequestBody dto: ProductGroupCreateDto): ProductGroupDto {
        return service.update(id, dto)
    }

    @RequestMapping("/update", method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody dtoList: List<ProductGroupDto>): List<ProductGroupDto> {
        return service.update(dtoList)
    }

    @RequestMapping("/{id}", method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Int) {
        return service.delete(id)
    }
}