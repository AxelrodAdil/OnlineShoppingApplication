package kz.axelrod.shop.domain.model.characters

data class ProductItem(
    val productId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)
