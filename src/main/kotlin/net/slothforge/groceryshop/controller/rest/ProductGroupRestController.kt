package net.slothforge.groceryshop.controller.rest

import net.slothforge.groceryshop.dto.ProductGroupDtoFull
import net.slothforge.groceryshop.dto.ProductGroupDtoUpdate
import net.slothforge.groceryshop.service.ProductGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/product-group")
class ProductGroupRestController {

    @Autowired
    private lateinit var productGroupService: ProductGroupService

    @RequestMapping("/all", method = [(RequestMethod.GET)])
    fun listAll(): List<ProductGroupDtoFull> {
        return productGroupService.listAll()
    }

    @RequestMapping("/add", method = [(RequestMethod.POST)])
    fun insert(@RequestBody dto: ProductGroupDtoUpdate): ProductGroupDtoFull {
        return productGroupService.add(dto)
    }

    @RequestMapping("/{id}", method = [(RequestMethod.PUT)])
    fun update(@PathVariable id: Long,
               @RequestBody dto: ProductGroupDtoUpdate): ProductGroupDtoFull {
        return productGroupService.update(id, dto)
    }

    @RequestMapping("/{id}", method = [(RequestMethod.DELETE)])
    fun delete(@PathVariable id: Long): Boolean {
        return productGroupService.delete(id)
    }
}