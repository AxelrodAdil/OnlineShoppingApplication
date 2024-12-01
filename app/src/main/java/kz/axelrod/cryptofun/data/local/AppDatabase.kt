package kz.axelrod.cryptofun.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.axelrod.cryptofun.data.model.user.UserDBModel

@Database(entities = [UserDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun onlineStoreDao(): AuthorizationDao
}