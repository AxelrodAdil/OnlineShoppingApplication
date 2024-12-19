package kz.axelrod.shop.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_data")
data class ProductData(
    @PrimaryKey val id: Long,
    val symbol: String,
    val avgPrice: Double,
    val lastUpdateId: Long,
    val bestBidPrice: Double,
    val bestBidQuantity: Double,
    val bestAskPrice: Double,
    val bestAskQuantity: Double
)
