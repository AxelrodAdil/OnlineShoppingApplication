package kz.axelrod.shop.data.model.products

data class ProductListApiModel(
    val products: List<ProductItemApiModel>?, // Matches "products" in the JSON response
)
