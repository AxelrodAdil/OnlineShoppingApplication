package kz.axelrod.shop.data.model.products

import com.google.gson.annotations.SerializedName

data class ProductItemApiModel(

    @SerializedName("productId")
    val productId: Int?,

    val name: String?,
    val description: String?,
    val price: Double?,
    val imageUrl: String?,
    val category: String?
)
