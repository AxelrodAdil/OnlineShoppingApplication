package kz.axelrod.shop.presentation.features.sign_up

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
import kz.axelrod.shop.presentation.common.UserField
import kz.axelrod.shop.presentation.model.TextFieldUiState
import kz.axelrod.shop.ui.theme.Paddings
import kz.axelrod.shop.utils.Screen
import kz.axelrod.shop.utils.extension.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val loadingView = remember { mutableStateOf(false) }
    val context = LocalContext.current
    LoaderPopup(loadingPopup = loadingView)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SignUpContract.Effect.OnLoading -> {
                    loadingView.value = true
                }
                SignUpContract.Effect.OnUserCreated -> {
                    loadingView.value = false
                    Toast.makeText(context, "User successfully created", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(Screen.SignIn.route)
                }
                SignUpContract.Effect.OnUserExist -> {
                    loadingView.value = false
                    Toast.makeText(context, "User already exist", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    SignUpContent(
        state = uiState,
        onLoginClicked = {
            viewModel.setEvent(SignUpContract.Event.OnSignUpClicked)
        },
        onSignUpClicked = {
            navController.navigate(Screen.SignIn.route)
        }
    )
}

@Composable
private fun SignUpContent(
    modifier: Modifier = Modifier,
    state: SignUpContract.State,
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
        SignUpFields(
            email = state.email,
            password = state.password,
            name = state.name
        )
        Spacer(modifier = Modifier.height(Paddings.extraLarge))
        SignUpButton(
            onLoginClicked = onLoginClicked, enabled = state.isButtonAvailable
        )
        Spacer(modifier = Modifier.height(Paddings.extraLarge))
        SignInButton(
            onSignInClicked = onSignUpClicked, enabled = state.isButtonAvailable
        )
    }
}
@Composable
private fun SignUpFields(
    email: TextFieldUiState,
    name: TextFieldUiState,
    password: TextFieldUiState
) {
    UserField(
        name = name
    )
    Spacer(modifier = Modifier.height(Paddings.extraLarge))
    EmailField(
        email = email
    )
    Spacer(modifier = Modifier.height(Paddings.extraLarge))
    PasswordField(
        password = password
    )
}

@Composable
private fun SignUpButton(enabled: Boolean, onLoginClicked: () -> Unit) {
    CustomYellowButton(onClick = onLoginClicked, enabled = enabled, content = {
        Text(
            text = "Sign up",
        )
    })
}

@Composable
private fun SignInButton(enabled: Boolean, onSignInClicked: () -> Unit) {
    CustomYellowButton(onClick = onSignInClicked, enabled = enabled, content = {
        Text(
            text = "Already have an account? Sign in",
            modifier = Modifier
                .wrapContentWidth()
                .noRippleClickable { onSignInClicked() }
                .padding(top = Paddings.small),
            color = MaterialTheme.colorScheme.primary,
        )
    })
}
