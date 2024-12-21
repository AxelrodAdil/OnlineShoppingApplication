package kz.axelrod.shop.data.model.category

import com.google.gson.annotations.SerializedName

data class CategoryItemApiModel(

    @SerializedName("categoryId")
    val categoryId: Int?,

    val categoryName: String?
)