package kz.axelrod.cryptofun.data.model.products

data class AvgPriceResponse(
    val mins: Int,
    val price: String,
    val closeTime: Long
)
