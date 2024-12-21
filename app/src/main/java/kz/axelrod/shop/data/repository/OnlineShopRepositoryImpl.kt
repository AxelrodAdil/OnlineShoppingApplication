package kz.axelrod.shop.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.axelrod.shop.data.api.OnlineShopService
//import kz.axelrod.shop.data.local.ProductDao
import kz.axelrod.shop.data.mapper.ProductItemsMapper
import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.domain.network.Response
import kz.axelrod.shop.domain.repository.OnlineShopRepository
import kz.axelrod.shop.utils.extension.catchError

class OnlineShopRepositoryImpl(
    private val service: OnlineShopService,
//    private val dao: ProductDao,
    private val mapper: ProductItemsMapper
) : OnlineShopRepository {

    override suspend fun getProductList(): Response<List<ProductItem>> = mapper.map(
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
}