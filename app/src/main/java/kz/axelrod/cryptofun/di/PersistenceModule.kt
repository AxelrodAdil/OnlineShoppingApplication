package kz.axelrod.cryptofun.di

import android.content.Context
import androidx.room.Room
import kz.axelrod.cryptofun.data.local.AppDatabase
import kz.axelrod.cryptofun.data.local.AuthorizationDao
import kz.axelrod.cryptofun.utils.Constants.LOCATION_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        LOCATION_DB
    ).build()
}

fun provideLocationDao(database: AppDatabase): AuthorizationDao {
    return database.onlineStoreDao()
}

val persistenceModule = module {
    single { provideAppDatabase(androidContext()) }
    single { provideLocationDao(get()) }
}
