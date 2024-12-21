package kz.axelrod.shop.data.model.category

import com.google.gson.annotations.SerializedName

data class CategoryListApiModel (

    @SerializedName("categories")
    val categories: List<CategoryItemApiModel>?, // Matches "categories" in the JSON response
)
