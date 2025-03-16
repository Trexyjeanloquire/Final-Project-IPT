package models

data class Product(
    private val productName: String?,
    private val productId: Int?,
    private val productCategory: String?,
    private val productBrand: String?,
    private val productPrice: Double?,
    private val productQuantity: Int?
){

    fun getProductName(): String?{
        return productName
    }
    fun getProductId(): Int?{
        return productId
    }
    fun getProductCategory(): String?{
        return productCategory
    }
    fun getProductBrand(): String?{
        return productBrand
    }
    fun getProductPrice(): Double?{
        return productPrice
    }
    fun getProductQuantity(): Int?{
        return productQuantity
    }


}
