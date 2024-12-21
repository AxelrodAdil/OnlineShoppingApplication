package kz.axelrod.shop.data.api

import kz.axelrod.shop.data.model.products.ProductListApiModel
import retrofit2.http.GET

interface OnlineShopService {

    @GET("6719602b-11ae-43ae-bc30-78cd1d930714")
    suspend fun getProductList(): ProductListApiModel
}