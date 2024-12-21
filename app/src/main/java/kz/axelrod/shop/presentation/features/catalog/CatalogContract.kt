package kz.axelrod.shop.presentation.features.catalog

import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.mvi.UiEffect
import kz.axelrod.shop.presentation.mvi.UiEvent
import kz.axelrod.shop.presentation.mvi.UiState

interface CatalogContract {

    sealed interface Event : UiEvent {
        data class LoadProducts(val categoryId: Int?, val searchQuery: String?) : Event
        data class ViewProductDetails(val productId: Int) : Event
        data object RefreshCatalog : Event
    }

    data class State(
        val productsState: ResourceUiState<List<ProductItem>> = ResourceUiState.Idle,
        val selectedProduct: ProductItem? = null
    ) : UiState

    sealed interface Effect : UiEffect {
        data class ProductsLoaded(val productCount: Int) : Effect
        data class ProductSelected(val productId: Int) : Effect
        data class Error(val message: String) : Effect
    }
}
