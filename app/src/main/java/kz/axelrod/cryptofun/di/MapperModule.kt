package kz.axelrod.cryptofun.di

import kz.axelrod.cryptofun.data.mapper.AuthorizationMapper
import kz.axelrod.cryptofun.data.mapper.CryptocurrencyItemsMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mapperModule = module {
    singleOf(::AuthorizationMapper)
    singleOf(::CryptocurrencyItemsMapper)
}
