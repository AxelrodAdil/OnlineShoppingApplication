package kz.axelrod.shop.data.model.products

import com.google.gson.annotations.SerializedName

data class ProductListApiModel(

    @SerializedName("products")
    val products: List<ProductItemApiModel>?, // Matches "products" in the JSON response
)
