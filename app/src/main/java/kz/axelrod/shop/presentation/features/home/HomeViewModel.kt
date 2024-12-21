package kz.axelrod.shop.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.axelrod.shop.domain.network.toResourceUiState
import kz.axelrod.shop.domain.repository.OnlineShopRepository
import kz.axelrod.shop.domain.use_case.GetProductListUseCase
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.mvi.BaseViewModel

class HomeViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val repository: OnlineShopRepository
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>() {

    init {
        getProducts()
    }

    private val _orderResult = MutableLiveData<String>()
    val orderResult: LiveData<String> get() = _orderResult

    override fun createInitialState(): HomeContract.State =
        HomeContract.State(
            productList = ResourceUiState.Idle
        )

    override fun handleEvent(event: HomeContract.Event) {}

    private fun getProducts() {
        setState { copy(productList = ResourceUiState.Loading) }
        viewModelScope.launch {
            val result = getProductListUseCase()
            setState {
                copy(
                    productList = result.toResourceUiState()
                )
            }
        }
    }
}
