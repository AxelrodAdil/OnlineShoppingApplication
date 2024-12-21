package kz.axelrod.shop.data.api

import kz.axelrod.shop.data.model.category.CategoryListApiModel
import kz.axelrod.shop.data.model.products.ProductListApiModel
import kz.axelrod.shop.utils.Constants
import retrofit2.http.GET

interface OnlineShopService {

    @GET(Constants.PRODUCTS_API)
    suspend fun getProductList(): ProductListApiModel

    @GET(Constants.CATEGORY_API)
    suspend fun getCategoryList(): CategoryListApiModel
}
