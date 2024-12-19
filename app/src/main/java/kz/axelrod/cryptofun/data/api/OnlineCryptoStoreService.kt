package kz.axelrod.cryptofun.data.api

import kz.axelrod.cryptofun.data.model.products.AvgPriceResponse
import kz.axelrod.cryptofun.data.model.products.CryptocurrencyListApiModel
import kz.axelrod.cryptofun.data.model.products.DepthResponse
import kz.axelrod.cryptofun.data.model.products.TestOrderResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OnlineCryptoStoreService {

    @GET("6719602b-11ae-43ae-bc30-78cd1d930714")
    suspend fun getProductList(): CryptocurrencyListApiModel

    @GET("/depth")
    suspend fun getDepth(@Query("symbol") symbol: String): DepthResponse

    @GET("/avgPrice")
    suspend fun getAvgPrice(@Query("symbol") symbol: String): AvgPriceResponse

    @POST("/order/test")
    suspend fun placeTestOrder(
        @Query("symbol") symbol: String,
        @Query("side") side: String,
        @Query("type") type: String,
        @Query("timeInForce") timeInForce: String,
        @Query("quantity") quantity: Double,
        @Query("price") price: Double,
        @Query("recvWindow") recvWindow: Long,
        @Query("timestamp") timestamp: Long,
        @Query("signature") signature: String
    ): TestOrderResponse
}