package kz.axelrod.cryptofun.di

import kz.axelrod.cryptofun.presentation.features.home.HomeViewModel
import kz.axelrod.cryptofun.presentation.features.sign_in.SigInViewModel
import kz.axelrod.cryptofun.presentation.features.sign_up.SigUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SigInViewModel)
    viewModelOf(::SigUpViewModel)
    viewModel {
        HomeViewModel(
            getCharactersUseCase = get(),
            repository = get()
        )
    }
}
