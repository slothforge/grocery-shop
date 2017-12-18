package net.slothforge.groceryshop.controller

import net.slothforge.groceryshop.dto.ProductGroupCreateDTO
import net.slothforge.groceryshop.entity.ProductGroup
import net.slothforge.groceryshop.service.ProductGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product-group")
class ProductGroupController {

    @Autowired
    private lateinit var productGroupService: ProductGroupService

    @RequestMapping("/all", method = [(RequestMethod.GET)])
    internal fun listAll(): List<ProductGroup> {
        return productGroupService.listAll()
    }

    @RequestMapping("/{id}", method = [(RequestMethod.GET)])
    internal fun findById(@PathVariable id: Long): ProductGroup? {
        return productGroupService.findById(id)
    }

    @RequestMapping("/add", method = [(RequestMethod.POST)])
    internal fun insert(@RequestBody dto: ProductGroupCreateDTO): ProductGroup? {
        return productGroupService.add(dto.name, dto.description)
    }

    @RequestMapping("/{id}", method = [(RequestMethod.PUT)])
    internal fun update(@PathVariable id: Long,
                        @RequestBody dto: ProductGroupCreateDTO): ProductGroup? {
        return productGroupService.update(id, dto.name, dto.description)
    }

    @RequestMapping("/{id}", method = [(RequestMethod.DELETE)])
    internal fun delete(@PathVariable id: Long): Boolean {
        return productGroupService.delete(id) > 0
    }
}