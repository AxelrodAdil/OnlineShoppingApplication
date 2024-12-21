package kz.axelrod.shop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.axelrod.shop.data.model.user.UserDBModel

@Database(
    entities = [
        UserDBModel::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun onlineStoreDao(): AuthorizationDao
}
