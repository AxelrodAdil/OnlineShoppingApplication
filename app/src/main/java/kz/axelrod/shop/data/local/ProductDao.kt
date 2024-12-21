package kz.axelrod.shop.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kz.axelrod.shop.data.model.category.CategoryDBModel
import kz.axelrod.shop.data.model.products.ProductDataDBModel

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductDataDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryDBModel)

    @Query("SELECT * FROM product_data WHERE categoryId = :categoryId")
    fun getProductsByCategory(categoryId: Int): Flow<List<ProductDataDBModel>>

    @Query("SELECT * FROM product_data WHERE name LIKE '%' || :name || '%'")
    fun searchProductsByName(name: String): Flow<List<ProductDataDBModel>>

    @Query("SELECT * FROM product_data WHERE id = :productId")
    fun getProductDetails(productId: Int): Flow<ProductDataDBModel>
}
