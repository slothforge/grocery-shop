package net.slothforge.groceryshop.repository

import net.slothforge.groceryshop.entity.ProductGroup
import org.springframework.data.repository.CrudRepository

interface ProductGroupRepository : CrudRepository<ProductGroup, Int>