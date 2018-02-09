package net.slothforge.groceryshop.controller.rest

import net.slothforge.groceryshop.dto.ProductDtoFull
import net.slothforge.groceryshop.dto.ProductDto
import net.slothforge.groceryshop.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/product")
class ProductRestController {

    @Autowired
    private lateinit var productService: ProductService

    @RequestMapping("/all", method = [(RequestMethod.GET)])
    fun listAll() = productService.listAll()

    @RequestMapping("/add", method = [(RequestMethod.POST)])
    fun insert(@RequestBody dto: ProductDto): ProductDtoFull {
        return productService.insert(dto)
    }

    @RequestMapping("/{id}", method = [(RequestMethod.PUT)])
    fun update(@PathVariable id: Long,
               @RequestBody dto: ProductDto): ProductDtoFull {
        return productService.update(id, dto)
    }

    @RequestMapping("/{id}", method = [(RequestMethod.DELETE)])
    fun delete(@PathVariable id: Long): Boolean {
        return productService.delete(id)
    }

}