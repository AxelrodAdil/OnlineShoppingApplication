package kz.axelrod.cryptofun.data.model.products

data class CryptocurrencyItemApiModel(
    val productId: Int?,
    val name: String?,
    val description: String?,
    val price: Double?,
    val imageUrl: String?,
    val category: String?
)
