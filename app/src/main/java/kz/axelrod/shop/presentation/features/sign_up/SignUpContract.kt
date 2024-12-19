package kz.axelrod.shop.presentation.features.sign_up

import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.model.TextFieldUiState
import kz.axelrod.shop.presentation.mvi.UiEffect
import kz.axelrod.shop.presentation.mvi.UiEvent
import kz.axelrod.shop.presentation.mvi.UiState

interface SignUpContract {

    sealed interface Event : UiEvent {
        data object OnSignUpClicked : Event
    }

    data class State(
        val state: ResourceUiState<Unit>,
        val name: TextFieldUiState,
        val email: TextFieldUiState,
        val password: TextFieldUiState,
        val isButtonAvailable: Boolean = false,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object OnLoading : Effect
        data object OnUserExist : Effect
        data object OnUserCreated : Effect
    }
}
