package kz.axelrod.cryptofun.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptocurrency_data")
data class CryptocurrencyData(
    @PrimaryKey val id: Long,
    val symbol: String,
    val avgPrice: Double,
    val lastUpdateId: Long,
    val bestBidPrice: Double,
    val bestBidQuantity: Double,
    val bestAskPrice: Double,
    val bestAskQuantity: Double
)
