package kz.axelrod.shop.di

import kz.axelrod.shop.data.mapper.AuthorizationMapper
import kz.axelrod.shop.data.mapper.ProductItemsMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mapperModule = module {
    singleOf(::AuthorizationMapper)
    singleOf(::ProductItemsMapper)
}
