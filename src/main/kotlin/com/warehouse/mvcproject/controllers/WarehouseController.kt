package com.warehouse.mvcproject.controllers

import com.warehouse.mvcproject.dto.ProductDto
import com.warehouse.mvcproject.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.expression.spel.SpelEvaluationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/warehouse")
class WarehouseController {
    @Autowired
    lateinit var productService: ProductService

    @PostMapping(path = ["/products"])
    @ResponseStatus(HttpStatus.CREATED)
     fun addProduct(@RequestBody productDto: ProductDto): ResponseEntity<Any> { //Flux<Product>
        return try {
            if (productDto != null)
                productService.addProduct(productDto)
            ResponseEntity.ok(productDto.Name)

        } catch (e: SpelEvaluationException) {
            ResponseEntity.badRequest().body("Bad Request Message")
        }
    }

    @PatchMapping("/products/{productID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
     fun patchProduct(@PathVariable productID: Long, @RequestBody quantity:  Long): ResponseEntity<Any> {
        return try {
            if (productID != null)
            {
                productService.patchProduct(productID,quantity)
                ResponseEntity.ok(productID)
            }
            else ResponseEntity.badRequest().body("Bad Request Message")
        } catch (e: SpelEvaluationException) {
            ResponseEntity.badRequest().body("Bad Request Message")
        }
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
     fun getAllProducts(): ResponseEntity<Any>? {
        var allProductList =productService.getAllProducts()?.map { it.toDTO() }

      return  ResponseEntity(allProductList,HttpStatus.OK)

    }

    @GetMapping("/products/{productID}")
     fun getProduct(@PathVariable productID: String):  ProductDto  {
        return productService.getProduct(productID.toLong()).toDTO()
    }

    @GetMapping("/productsByCategory?category={category}")
    @ResponseStatus(HttpStatus.OK)
     fun getAllProductsByCategory(@PathVariable category: String): MutableIterable<ProductDto>? {
        return productService.getAllProductsByCategory(category)
    }

}