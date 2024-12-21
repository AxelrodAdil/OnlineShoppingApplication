package kz.axelrod.shop.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.axelrod.shop.R
import kz.axelrod.shop.data.api.OnlineShopService
import kz.axelrod.shop.data.local.ProductDao
import kz.axelrod.shop.data.mapper.ProductItemsMapper
import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.domain.network.Response
import kz.axelrod.shop.domain.repository.OnlineShopRepository
import kz.axelrod.shop.utils.UiText
import kz.axelrod.shop.utils.extension.catchError

class OnlineShopRepositoryImpl(
    private val service: OnlineShopService,
    private val mapper: ProductItemsMapper,
    private val productDao: ProductDao
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

    override suspend fun getProducts(
        categoryId: Int?,
        searchQuery: String?
    ): Response<List<ProductItem>> {
        return try {
            withContext(Dispatchers.IO) {
                val productListApiModel = service.getProductList()
                val productList = mapper.mapData(productListApiModel)

                val filteredProducts = categoryId?.let { id ->
                    productList.filter { it.category == id.toString() }
                } ?: productList

                val searchedProducts = searchQuery?.let { query ->
                    filteredProducts.filter { it.name.contains(query, ignoreCase = true) }
                } ?: filteredProducts
                Response.Success(searchedProducts)
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getProductDetails(productId: Int): Response<ProductItem> {
        return try {
            withContext(Dispatchers.IO) {
                val productListApiModel = service.getProductList()
                val productList = mapper.mapData(productListApiModel)
                val product = productList.find { it.productId == productId }
                    ?: return@withContext Response.Error(UiText.StringResource(R.string.product_not_found))
                Response.Success(product)
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }
}
