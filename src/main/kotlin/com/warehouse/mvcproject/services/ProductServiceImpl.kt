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
        return try {     productRepository.findAll()
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
            productRepository.findProductById(id)
        } catch(ex: Exception) {
            throw ex
        }
    }
    override  fun patchProduct(productID :Long, value:   Long)  {
       productRepository.updateQuantityOfProduct(productID, value)
    }
    override  fun addProduct(productdto: ProductDto){
        val product=Product(id=null, name = productdto.Name,category = productdto.Category,
            price = productdto.Price,quantity = productdto.Quantity)
        productRepository.save(product)
    }


}


