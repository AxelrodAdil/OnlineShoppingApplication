package kz.axelrod.shop.utils

import android.app.Application
import kz.axelrod.shop.di.mapperModule
import kz.axelrod.shop.di.networkModule
import kz.axelrod.shop.di.persistenceModule
import kz.axelrod.shop.di.repositoryModule
import kz.axelrod.shop.di.useCasesModule
import kz.axelrod.shop.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ShopApplication)
            modules(
                repositoryModule,
                useCasesModule,
                viewModelModule,
                networkModule,
                mapperModule,
                persistenceModule
            )
        }
    }
}
