package kz.axelrod.cryptofun.domain.repository

import kz.axelrod.cryptofun.domain.model.characters.CryptocurrencyItem
import kz.axelrod.cryptofun.domain.network.Response

interface OnlineCryptoStoreRepository {

    suspend fun getCharacters(): Response<List<CryptocurrencyItem>>

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