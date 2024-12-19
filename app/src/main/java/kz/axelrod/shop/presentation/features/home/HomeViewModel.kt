package kz.axelrod.shop.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.axelrod.shop.domain.network.Response
import kz.axelrod.shop.domain.network.toResourceUiState
import kz.axelrod.shop.domain.repository.OnlineShopRepository
import kz.axelrod.shop.domain.use_case.GetCharactersUseCase
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.mvi.BaseViewModel

class HomeViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
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
            val result = getCharactersUseCase()
            setState {
                copy(
                    productList = result.toResourceUiState()
                )
            }
        }
    }

    fun placeTestOrder(
        symbol: String,
        side: String,
        type: String,
        timeInForce: String,
        quantity: Double,
        price: Double,
        recvWindow: Long,
        timestamp: Long,
        signature: String
    ) {
        viewModelScope.launch {
            val result = repository.handleOrderTest(
                symbol, side, type, timeInForce, quantity, price, recvWindow, timestamp, signature
            )
            if (result is Response.Success) {
                _orderResult.postValue(result.data)
            } else if (result is Response.Error) {
                _orderResult.postValue(result.error.toString())
            }
        }
    }
}
