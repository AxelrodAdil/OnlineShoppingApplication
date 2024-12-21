package kz.axelrod.shop.presentation.features.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import kz.axelrod.shop.domain.model.characters.ProductItem
import kz.axelrod.shop.presentation.state.ManagementResourceUiState
import kz.axelrod.shop.presentation.state.ComponentRectangleLineLong
import kz.axelrod.shop.ui.theme.Paddings
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogScreen(
    navController: NavHostController,
    viewModel: CatalogViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CatalogContent(
        state = uiState,
        onCategorySelected = { categoryId ->
            viewModel.setEvent(CatalogContract.Event.LoadProducts(categoryId, null))
        },
        onSearchQueryChanged = { query ->
            viewModel.setEvent(CatalogContract.Event.LoadProducts(null, query))
        },
        onProductClicked = { productId ->
            viewModel.setEvent(CatalogContract.Event.ViewProductDetails(productId))
            navController.navigate("productDetails/$productId")
        },
        onRefreshClicked = {
            viewModel.setEvent(CatalogContract.Event.RefreshCatalog)
        }
    )
}

@Composable
fun CatalogContent(
    state: CatalogContract.State,
    onCategorySelected: (Int?) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onProductClicked: (Int) -> Unit,
    onRefreshClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Paddings.medium)
    ) {
        var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onSearchQueryChanged(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Paddings.small),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Paddings.small)
                ) {
                    if (searchQuery.text.isEmpty()) {
                        Text(text = "Search products...")
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(Paddings.small))
        Button(
            onClick = onRefreshClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Refresh Catalog")
        }
        Spacer(modifier = Modifier.height(Paddings.medium))
        ManagementResourceUiState(
            resourceUiState = state.productsState,
            successView = { products ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(products.size) { index ->
                        ProductItemView(
                            product = products[index],
                            onClick = { onProductClicked(products[index].productId) }
                        )
                    }
                }
            },
            loadingView = {
                repeat(5) {
                    ComponentRectangleLineLong(
                        modifier = Modifier
                            .padding(Paddings.small)
                            .fillMaxWidth()
                    )
                }
            },
            onTryAgain = { onRefreshClicked() },
            msgTryAgain = "Failed to load products. Please try again.",
            onCheckAgain = {}
        )
    }
}

@Composable
fun ProductItemView(product: ProductItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(Paddings.medium)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
        Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Price: ${product.price}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Rating: ${product.rating}", style = MaterialTheme.typography.bodySmall)
    }
}
