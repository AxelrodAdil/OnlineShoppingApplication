package kz.axelrod.cryptofun.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.axelrod.cryptofun.data.model.user.CryptocurrencyData

@Dao
interface CryptocurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrency(data: CryptocurrencyData)

    @Query("SELECT * FROM cryptocurrency_data WHERE symbol = :symbol")
    fun getCryptocurrency(symbol: String): LiveData<CryptocurrencyData>
}
