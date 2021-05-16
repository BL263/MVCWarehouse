package com.warehouse.mvcproject.services



import com.warehouse.mvcproject.domain.Product
import com.warehouse.mvcproject.dto.ProductDto
import com.warehouse.mvcproject.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ProductServiceImpl : ProductService {

@Autowired
lateinit var productRepository: ProductRepository

     override fun getAllProducts(): List<Product> {
        return try {
            productRepository.findAll()
        } catch(ex: Exception) {
            throw ex
        }
    }
    override fun getAllProductsByCategory(category: String):MutableIterable<ProductDto>?   {
        return try {
              productRepository.findProductByCategory(category).map { it.toDTO() }.toMutableList()
        } catch(ex: Exception) {
            throw ex
        }
    }
    override  fun getProduct(id:Long):  Product {
        return try {
          val product=  productRepository.findById(id).get()
            product
        } catch(ex: Exception) {
            throw ex
        }
    }
    override  fun patchProduct(productID :Long, value:   Long)  {
        val product = productRepository.findById(productID).get()
            if (product.id != null && (product.quantity?.toLong()?.plus(value))!! > 0)
                product.quantity=  ( product.quantity!!.toLong()+value).toString()
             productRepository.save(product)
    }
    override  fun addProduct(productdto: ProductDto){
        val product=Product(id=null, name = productdto.Name,category = productdto.Category,
            price = productdto.Price,quantity = productdto.Quantity)
        productRepository.save(product)
    }


}


