package com.warehouse.mvcproject.services

import com.warehouse.mvcproject.domain.Product
import com.warehouse.mvcproject.dto.ProductDto
import org.springframework.stereotype.Service


interface ProductService {
     fun getAllProducts(): List<Product>
     fun getAllProductsByCategory(category: String): MutableIterable<ProductDto>?
     fun getProduct(id:Long): Product
     fun patchProduct(productID: Long, quantity: Long)
     fun addProduct(product: ProductDto)

}