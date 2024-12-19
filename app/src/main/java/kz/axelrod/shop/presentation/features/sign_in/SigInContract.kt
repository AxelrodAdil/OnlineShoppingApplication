package kz.axelrod.shop.presentation.features.sign_in

import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.model.TextFieldUiState
import kz.axelrod.shop.presentation.mvi.UiEffect
import kz.axelrod.shop.presentation.mvi.UiEvent
import kz.axelrod.shop.presentation.mvi.UiState

interface SigInContract {
    sealed interface Event : UiEvent {
        data object OnLoginClicked : Event
    }

    data class State(
        val loginState: ResourceUiState<Unit>,
        val email: TextFieldUiState,
        val password: TextFieldUiState,
        val isButtonAvailable: Boolean = false,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object OnUserFetched : Effect
        data object OnUserDoNotExist : Effect
        data object OnLoading : Effect
    }
}