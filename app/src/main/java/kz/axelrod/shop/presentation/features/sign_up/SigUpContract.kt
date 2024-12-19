package kz.axelrod.shop.presentation.features.sign_up

import kz.axelrod.shop.presentation.mvi.UiEffect
import kz.axelrod.shop.presentation.mvi.UiEvent
import kz.axelrod.shop.presentation.mvi.UiState

interface SigUpContract {

    sealed interface Event : UiEvent
    data object State : UiState
    sealed interface Effect : UiEffect
}