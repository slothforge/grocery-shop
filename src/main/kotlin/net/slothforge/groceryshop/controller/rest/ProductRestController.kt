package net.slothforge.groceryshop.controller.rest

import net.slothforge.groceryshop.dto.ProductDto
import net.slothforge.groceryshop.dto.create.ProductCreateDto
import net.slothforge.groceryshop.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("rest/product")
class ProductRestController {

    @Autowired
    private lateinit var service: ProductService

    @RequestMapping("/all", method = [RequestMethod.GET])
    fun listAll(): List<ProductDto> {
        return service.listAll()
    }

    @RequestMapping("/{id}", method = [RequestMethod.GET])
    fun findById(@PathVariable id: Int): ProductDto {
        return service.findById(id)
    }

    @RequestMapping("/add", method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody dtoList: List<ProductCreateDto>): List<ProductDto> {
        return service.insert(dtoList)
    }

    @RequestMapping("/{id}", method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Int, @RequestBody dto: ProductCreateDto): ProductDto {
        return service.update(id, dto)
    }

    @RequestMapping("/update", method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody dtoList: List<ProductDto>) : List<ProductDto> {
        return service.update(dtoList)
    }

    @RequestMapping("/{id}", method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Int) {
        return service.delete(id)
    }

}