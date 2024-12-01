package kz.axelrod.cryptofun.presentation.features.sign_up

import kz.axelrod.cryptofun.presentation.mvi.UiEffect
import kz.axelrod.cryptofun.presentation.mvi.UiEvent
import kz.axelrod.cryptofun.presentation.mvi.UiState

interface SigUpContract {

    sealed interface Event : UiEvent
    data object State : UiState
    sealed interface Effect : UiEffect
}