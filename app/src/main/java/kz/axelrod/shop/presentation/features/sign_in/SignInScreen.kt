package kz.axelrod.shop.presentation.features.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kz.axelrod.shop.presentation.common.CustomYellowButton
import kz.axelrod.shop.presentation.common.EmailField
import kz.axelrod.shop.presentation.common.LoaderPopup
import kz.axelrod.shop.presentation.common.PasswordField
import kz.axelrod.shop.presentation.model.TextFieldUiState
import kz.axelrod.shop.ui.theme.Paddings
import kz.axelrod.shop.utils.Screen
import kz.axelrod.shop.utils.extension.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SigInViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val loadingView = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LoaderPopup(loadingPopup = loadingView)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SigInContract.Effect.OnUserFetched -> {
                    loadingView.value = false
                    navController.navigate(Screen.Home.route)
                }

                SigInContract.Effect.OnUserDoNotExist -> {
                    loadingView.value = false
                    Toast.makeText(context, "User does not exist", Toast.LENGTH_SHORT)
                        .show()
                }

                SigInContract.Effect.OnLoading -> {
                    loadingView.value = true
                }
            }
        }
    }
    LoginContent(
        state = uiState,
        onLoginClicked = {
            viewModel.setEvent(SigInContract.Event.OnLoginClicked)
        },
        onSignUpClicked = {
            navController.navigate(Screen.SignUp.route)
        }
    )
}

@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    state: SigInContract.State,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Paddings.medium),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(Paddings.extraLarge))
        LoginFields(
            email = state.email,
            password = state.password
        )
        Spacer(modifier = Modifier.height(Paddings.extraLarge))
        LoginButton(
            onLoginClicked = onLoginClicked, enabled = state.isButtonAvailable
        )
        Spacer(modifier = Modifier.height(Paddings.extraLarge))
        SignUpButton(
            onSignUpClicked = onSignUpClicked, enabled = state.isButtonAvailable
        )
    }
}

@Composable
private fun SignUpButton(enabled: Boolean, onSignUpClicked: () -> Unit) {
    CustomYellowButton(onClick = onSignUpClicked, enabled = enabled, content = {
        Text(
            text = "Don't have an account? Sign up",
            modifier = Modifier
                .wrapContentWidth()
                .noRippleClickable { onSignUpClicked() }
                .padding(top = Paddings.small),
            color = MaterialTheme.colorScheme.primary,
        )
    })
}

@Composable
private fun LoginButton(enabled: Boolean, onLoginClicked: () -> Unit) {
    CustomYellowButton(onClick = onLoginClicked, enabled = enabled, content = {
        Text(
            text = "Login",
        )
    })
}

@Composable
private fun LoginFields(
    email: TextFieldUiState,
    password: TextFieldUiState
) {
    EmailField(
        email = email
    )
    Spacer(modifier = Modifier.height(Paddings.extraLarge))
    PasswordField(
        password = password
    )
}
