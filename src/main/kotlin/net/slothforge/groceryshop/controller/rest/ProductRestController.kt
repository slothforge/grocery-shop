package net.slothforge.groceryshop.controller.rest

import net.slothforge.groceryshop.dto.ProductDtoUpdate
import net.slothforge.groceryshop.entity.Product
import net.slothforge.groceryshop.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/product")
class ProductRestController {

    @Autowired
    private lateinit var productService: ProductService

// TODO
//    @RequestMapping("/all", method = [(RequestMethod.GET)])
//    fun listAll() = productService.listAllFullDto()
//
//    @RequestMapping("/add", method = [(RequestMethod.POST)])
//    fun insert(@RequestBody dto: ProductDtoUpdate): Product? {
//        return productService.add(dto)
//    }
//
//    @RequestMapping("/{id}", method = [(RequestMethod.PUT)])
//    fun update(@PathVariable id: Long,
//               @RequestBody dto: ProductDtoUpdate): Product? {
//        return productService.update(id, dto)
//    }
//
//    @RequestMapping("/{id}", method = [(RequestMethod.DELETE)])
//    fun delete(@PathVariable id: Long): Boolean {
//        return productService.delete(id)
//    }

}