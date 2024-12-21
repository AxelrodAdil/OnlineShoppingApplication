package kz.axelrod.shop.domain.repository

import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.domain.network.Response

interface OnlineShopRepository {

    suspend fun getProductList(): Response<List<ProductItem>>
    suspend fun getProducts(categoryId: Int? = null, searchQuery: String? = null): Response<List<ProductItem>>
    suspend fun getProductDetails(productId: Int): Response<ProductItem>
}
