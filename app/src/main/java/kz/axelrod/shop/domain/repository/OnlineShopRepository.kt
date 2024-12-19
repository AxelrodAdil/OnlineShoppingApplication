package kz.axelrod.shop.domain.repository

import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.domain.network.Response

interface OnlineShopRepository {

    suspend fun getCharacters(): Response<List<ProductItem>>

    suspend fun handleOrderTest(
        symbol: String,
        side: String,
        type: String,
        timeInForce: String,
        quantity: Double,
        price: Double,
        recvWindow: Long,
        timestamp: Long,
        signature: String
    ): Response<String>
}