package kz.axelrod.shop.presentation.features.sign_in

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.axelrod.shop.domain.model.TextInput
import kz.axelrod.shop.domain.repository.AuthorizationRepository
import kz.axelrod.shop.domain.use_case.CreateAuthTokenUseCase
import kz.axelrod.shop.domain.use_case.ValidateEmail
import kz.axelrod.shop.domain.use_case.ValidateField
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.model.TextFieldUiState
import kz.axelrod.shop.presentation.model.ValidationResult
import kz.axelrod.shop.presentation.mvi.BaseViewModel
import kz.axelrod.shop.utils.UiText

class SigInViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidateField,
    private val repository: AuthorizationRepository,
    private val createAuthTokenUseCase: CreateAuthTokenUseCase
) :
    BaseViewModel<SigInContract.State, SigInContract.Event, SigInContract.Effect>() {

    override fun createInitialState(): SigInContract.State =
        SigInContract.State(
            email = TextFieldUiState(
                input = mutableStateOf(""),
                validationResult = mutableStateOf(ValidationResult(successful = true)),
                onFieldChanged = {
                    validateAllFieldsAndUpdateButtonState(
                        currentState,
                        TextInput.EMAIL_ADDRESS
                    )
                }
            ),
            password = TextFieldUiState(
                input = mutableStateOf(""),
                validationResult = mutableStateOf(ValidationResult(successful = true)),
                onFieldChanged = {
                    validateAllFieldsAndUpdateButtonState(
                        currentState,
                        TextInput.PASSWORD
                    )
                }
            ),
            loginState = ResourceUiState.Idle
        )

    override fun handleEvent(event: SigInContract.Event) {
        when (event) {
            SigInContract.Event.OnLoginClicked -> {
                println("Login clicked")
                loginUser(state.email.input.value, state.password.input.value)
            }
        }
    }

    private fun validateAllFieldsAndUpdateButtonState(
        currentState: SigInContract.State,
        fieldChanged: TextInput
    ) {
        with(currentState) {
            val validateEmailResult = validateEmail(email.input.value, email.textInput)
            val validatePasswordResult = validatePassword(
                password.input.value,
                password.textInput
            )

            when (fieldChanged) {
                TextInput.EMAIL_ADDRESS -> email.validationResult.value = validateEmailResult
                TextInput.PASSWORD -> password.validationResult.value = validatePasswordResult
                else -> {}
            }

            setState {
                copy(
                    isButtonAvailable = validateEmailResult.successful
                            && validatePasswordResult.successful
                )
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                setState {
                    copy(
                        loginState = ResourceUiState.Loading
                    )
                }
                setEffect {
                    SigInContract.Effect.OnLoading
                }
                delay(2000)

                val userId = repository.loginUser(
                    email,
                    password
                )
                setEffect {
                    if (userId != null) {
                        createAuthToken(userId)
                        return@setEffect SigInContract.Effect.OnUserFetched
                    } else {
                        return@setEffect SigInContract.Effect.OnUserDoNotExist
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                setState {
                    copy(loginState = ResourceUiState.Error(UiText.DynamicString("An unexpected error occurred")))
                }
            }
        }
    }

    private fun createAuthToken(userId: Int) {
        createAuthTokenUseCase(userId)
    }
}
