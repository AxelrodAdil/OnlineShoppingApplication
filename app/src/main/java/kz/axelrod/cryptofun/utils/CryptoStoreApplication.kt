package kz.axelrod.cryptofun.utils

import android.app.Application
import kz.axelrod.cryptofun.di.mapperModule
import kz.axelrod.cryptofun.di.networkModule
import kz.axelrod.cryptofun.di.persistenceModule
import kz.axelrod.cryptofun.di.repositoryModule
import kz.axelrod.cryptofun.di.useCasesModule
import kz.axelrod.cryptofun.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CryptoStoreApplication)
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
