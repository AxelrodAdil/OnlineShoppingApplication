package kz.axelrod.cryptofun.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.axelrod.cryptofun.data.api.OnlineCryptoStoreService
//import kz.axelrod.cryptofun.data.local.CryptocurrencyDao
import kz.axelrod.cryptofun.data.mapper.CryptocurrencyItemsMapper
import kz.axelrod.cryptofun.data.model.user.CryptocurrencyData
import kz.axelrod.cryptofun.domain.model.characters.CryptocurrencyItem
import kz.axelrod.cryptofun.domain.network.Response
import kz.axelrod.cryptofun.domain.repository.OnlineCryptoStoreRepository
import kz.axelrod.cryptofun.utils.extension.catchError

class OnlineCryptoStoreRepositoryImpl(
    private val service: OnlineCryptoStoreService,
//    private val dao: CryptocurrencyDao,
    private val mapper: CryptocurrencyItemsMapper
) : OnlineCryptoStoreRepository {

    override suspend fun getCharacters(): Response<List<CryptocurrencyItem>> = mapper.map(
        try {
            withContext(Dispatchers.IO) {
                val data = service.getProductList()
                Response.Success(data)
            }
        } catch (e: Exception) {
            e.catchError()
        }
    )

    override suspend fun handleOrderTest(
        symbol: String,
        side: String,
        type: String,
        timeInForce: String,
        quantity: Double,
        price: Double,
        recvWindow: Long,
        timestamp: Long,
        signature: String
    ): Response<String> {
        return try {
            withContext(Dispatchers.IO) {
                service.placeTestOrder(
                    symbol, side, type, timeInForce, quantity, price, recvWindow, timestamp, signature
                )
                Response.Success("Order placed successfully")
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

//    suspend fun fetchAndSaveDepth(symbol: String) {
//        val depth = service.getDepth(symbol)
//        val avgPrice = service.getAvgPrice(symbol)
//        val bids = depth.getParsedBids()
//        val asks = depth.getParsedAsks()
//
//        val bestBid = bids.maxByOrNull { it.price }
//        val bestAsk = asks.minByOrNull { it.price }
//        val data = CryptocurrencyData(
//            id = depth.lastUpdateId,
//            symbol = symbol,
//            avgPrice = avgPrice.price.toDouble(),
//            lastUpdateId = depth.lastUpdateId,
//            bestBidPrice = bestBid?.price ?: 0.0,
//            bestBidQuantity = bestBid?.quantity ?: 0.0,
//            bestAskPrice = bestAsk?.price ?: 0.0,
//            bestAskQuantity = bestAsk?.quantity ?: 0.0
//        )
//        dao.insertCryptocurrency(data)
//    }

//    fun getCryptocurrency(symbol: String): LiveData<CryptocurrencyData> {
//        return dao.getCryptocurrency(symbol)
//    }
}