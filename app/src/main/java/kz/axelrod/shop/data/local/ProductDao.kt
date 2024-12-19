package kz.axelrod.shop.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.axelrod.shop.data.model.user.ProductData

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(data: ProductData)

    @Query("SELECT * FROM product_data WHERE symbol = :symbol")
    fun getProduct(symbol: String): LiveData<ProductData>
}
