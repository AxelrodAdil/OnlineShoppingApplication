package kz.axelrod.shop.di

import kz.axelrod.shop.presentation.features.home.HomeViewModel
import kz.axelrod.shop.presentation.features.sign_in.SigInViewModel
import kz.axelrod.shop.presentation.features.sign_up.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SigInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModel {
        HomeViewModel(
            getCharactersUseCase = get(),
            repository = get()
        )
    }
}
