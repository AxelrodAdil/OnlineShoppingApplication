package kz.axelrod.cryptofun.presentation.features.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import kz.axelrod.cryptofun.domain.model.characters.CryptocurrencyItem
import kz.axelrod.cryptofun.presentation.state.ComponentRectangleLineLong
import kz.axelrod.cryptofun.presentation.state.ManagementResourceUiState
import kz.axelrod.cryptofun.ui.theme.Paddings
import kz.axelrod.cryptofun.utils.extension.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val orderResult by viewModel.orderResult.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Paddings.medium)
    ) {
        Button(onClick = {
            viewModel.placeTestOrder(
                symbol = "XRPUSDT",
                side = "BUY",
                type = "LIMIT",
                timeInForce = "GTC",
                quantity = 1.0,
                price = 1.88951271,
                recvWindow = 5000L,
                timestamp = System.currentTimeMillis(),
                signature = "signature"
            )
        }) {
            Text("Place Test Order")
        }

        DisplayOrderResult(orderResult)

        ManagementResourceUiState(
            modifier = Modifier.padding(bottom = Paddings.medium),
            resourceUiState = state.productList,
            successView = {
                Column {
                    it.forEach {
                        CryptocurrencyItem(
                            productItem = it,
                            onClick = {
                            }
                        )
                    }
                }
            },
            loadingView = {
                Column {
                    for (i in 1..5) {
                        ItemOnLoading()
                    }
                }
            },
            onCheckAgain = {},
            onTryAgain = {},
        )
    }
}

@Composable
fun CryptocurrencyItem(
    modifier: Modifier = Modifier,
    productItem: CryptocurrencyItem,
    onClick: (CryptocurrencyItem) -> Unit
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
            .wrapContentHeight()
            .noRippleClickable { onClick(productItem) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = productItem.name,
                maxLines = 1,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productItem.name,
                    maxLines = 1,
                )
                Text(
                    text = productItem.description,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun ItemOnLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
            .wrapContentHeight()
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ComponentRectangleLineLong()
        }
    }
}

@Composable
fun DisplayOrderResult(result: String) {
    if (result.isNotEmpty()) {
        Toast.makeText(LocalContext.current, result, Toast.LENGTH_LONG).show()
    }
}
