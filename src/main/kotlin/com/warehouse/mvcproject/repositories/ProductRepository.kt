package com.warehouse.mvcproject.repositories


import com.warehouse.mvcproject.domain.Product
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, Long> {
     fun findProductById(id: Long): Product
   fun findProductByCategory(category: String): MutableList<Product>
    override fun findAll(): List<Product>
    @Modifying
    @Query("update product set quantity = quantity + ?1 where product_id = ?2", nativeQuery = true)
     fun updateQuantityOfProduct( id:Long,  value:Long)

}

