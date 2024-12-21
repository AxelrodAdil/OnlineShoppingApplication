package kz.axelrod.shop.data.model.products

import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.axelrod.shop.utils.Constants.PRODUCT_DATA

@Entity(
    tableName = PRODUCT_DATA
)
data class ProductDataDBModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val description: String,
    val price: Double,
    val rating: Float,
    val imageUrl: String?,
    val categoryId: Int
)
