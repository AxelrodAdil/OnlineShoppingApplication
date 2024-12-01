package kz.axelrod.cryptofun.presentation.features.sign_in

import kz.axelrod.cryptofun.presentation.model.ResourceUiState
import kz.axelrod.cryptofun.presentation.model.TextFieldUiState
import kz.axelrod.cryptofun.presentation.mvi.UiEffect
import kz.axelrod.cryptofun.presentation.mvi.UiEvent
import kz.axelrod.cryptofun.presentation.mvi.UiState

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
