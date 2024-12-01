package kz.axelrod.cryptofun.data.model.products

data class CryptocurrencyListApiModel(
    val products: List<CryptocurrencyItemApiModel>?, // Matches "products" in the JSON response
)
