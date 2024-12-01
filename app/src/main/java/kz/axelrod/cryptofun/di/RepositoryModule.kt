package kz.axelrod.cryptofun.di

import kz.axelrod.cryptofun.data.repository.AuthTokenRepositoryImpl
import kz.axelrod.cryptofun.data.repository.AuthorizationRepositoryImpl
import kz.axelrod.cryptofun.data.repository.OnlineCryptoStoreRepositoryImpl
import kz.axelrod.cryptofun.domain.repository.AuthTokenRepository
import kz.axelrod.cryptofun.domain.repository.AuthorizationRepository
import kz.axelrod.cryptofun.domain.repository.OnlineCryptoStoreRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::AuthorizationRepositoryImpl).bind(AuthorizationRepository::class)
    factoryOf(::AuthTokenRepositoryImpl).bind(AuthTokenRepository::class)
    factoryOf(::OnlineCryptoStoreRepositoryImpl).bind(OnlineCryptoStoreRepository::class)
}
