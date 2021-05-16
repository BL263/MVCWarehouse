package com.warehouse.mvcproject.domain

import com.warehouse.mvcproject.dto.ProductDto
import javax.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String? = null,
    var category: String? = null,
    var price: String? = null,
    var quantity: String? = null,

    ){
    fun toDTO(): ProductDto {
    return ProductDto(this.name, this.category, this.price, this.quantity)
    }
}