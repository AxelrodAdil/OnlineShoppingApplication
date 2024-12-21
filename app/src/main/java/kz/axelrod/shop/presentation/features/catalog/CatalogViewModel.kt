package kz.axelrod.shop.presentation.features.catalog

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.axelrod.shop.domain.network.Response.Companion.onError
import kz.axelrod.shop.domain.network.Response.Companion.onSuccess
import kz.axelrod.shop.domain.repository.OnlineShopRepository
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.mvi.BaseViewModel

class CatalogViewModel(
    private val repository: OnlineShopRepository
) : BaseViewModel<CatalogContract.State, CatalogContract.Event, CatalogContract.Effect>() {

    override fun createInitialState(): CatalogContract.State = CatalogContract.State()

    override fun handleEvent(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.LoadProducts -> loadProducts(
                event.categoryId,
                event.searchQuery
            )

            is CatalogContract.Event.ViewProductDetails -> selectProduct(event.productId)
            CatalogContract.Event.RefreshCatalog -> refreshCatalog()
        }
    }

    private fun loadProducts(categoryId: Int?, searchQuery: String?) {
        setState { copy(productsState = ResourceUiState.Loading) }

        viewModelScope.launch {
            val response = repository.getProducts(categoryId, searchQuery)
            response.onSuccess { products ->
                setState {
                    copy(productsState = ResourceUiState.Success(products))
                }
                setEffect { CatalogContract.Effect.ProductsLoaded(products.size) }
            }.onError { error ->
                setState {
                    copy(productsState = ResourceUiState.Error(error))
                }
                setEffect { CatalogContract.Effect.Error(error.toString()) }
            }
        }
    }

    private fun selectProduct(productId: Int) {
        setState { copy(productsState = ResourceUiState.Loading) }

        viewModelScope.launch {
            val response = repository.getProductDetails(productId)
            response.onSuccess { product ->
                setState { copy(selectedProduct = product) }
                setEffect { CatalogContract.Effect.ProductSelected(product.productId) }
            }.onError { error ->
                setState { copy(productsState = ResourceUiState.Error(error)) }
                setEffect { CatalogContract.Effect.Error(error.toString()) }
            }
        }
    }

    private fun refreshCatalog() {
        loadProducts(null, null)
    }
}
