package kz.axelrod.shop.presentation.features.home

import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.mvi.UiEffect
import kz.axelrod.shop.presentation.mvi.UiEvent
import kz.axelrod.shop.presentation.mvi.UiState

interface HomeContract {

    sealed interface Event : UiEvent    // input

    data class State(
        val productList: ResourceUiState<List<ProductItem>>
    ) : UiState

    sealed interface Effect : UiEffect      // output
}
