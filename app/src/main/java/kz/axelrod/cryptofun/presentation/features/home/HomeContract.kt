package kz.axelrod.cryptofun.presentation.features.home

import kz.axelrod.cryptofun.domain.model.characters.CryptocurrencyItem
import kz.axelrod.cryptofun.presentation.model.ResourceUiState
import kz.axelrod.cryptofun.presentation.mvi.UiEffect
import kz.axelrod.cryptofun.presentation.mvi.UiEvent
import kz.axelrod.cryptofun.presentation.mvi.UiState

interface HomeContract {
    sealed interface Event : UiEvent

    data class State(
        val productList: ResourceUiState<List<CryptocurrencyItem>>
    ) : UiState

    sealed interface Effect : UiEffect
}
