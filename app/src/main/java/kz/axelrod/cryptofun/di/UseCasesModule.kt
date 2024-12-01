package kz.axelrod.cryptofun.di

import kz.axelrod.cryptofun.domain.use_case.CreateAuthTokenUseCase
import kz.axelrod.cryptofun.domain.use_case.GetCharactersUseCase
import kz.axelrod.cryptofun.domain.use_case.ValidateEmail
import kz.axelrod.cryptofun.domain.use_case.ValidateField
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCasesModule: Module = module {
    factoryOf(::ValidateEmail)
    factoryOf(::ValidateField)
    factoryOf(::CreateAuthTokenUseCase)
    factoryOf(::GetCharactersUseCase)
}
