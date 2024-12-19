package kz.axelrod.shop.di

import kz.axelrod.shop.data.repository.AuthTokenRepositoryImpl
import kz.axelrod.shop.data.repository.AuthorizationRepositoryImpl
import kz.axelrod.shop.data.repository.OnlineShopRepositoryImpl
import kz.axelrod.shop.domain.repository.AuthTokenRepository
import kz.axelrod.shop.domain.repository.AuthorizationRepository
import kz.axelrod.shop.domain.repository.OnlineShopRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::AuthorizationRepositoryImpl).bind(AuthorizationRepository::class)
    factoryOf(::AuthTokenRepositoryImpl).bind(AuthTokenRepository::class)
    factoryOf(::OnlineShopRepositoryImpl).bind(OnlineShopRepository::class)
}
