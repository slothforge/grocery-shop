package net.slothforge.groceryshop.repository

import net.slothforge.groceryshop.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Int>