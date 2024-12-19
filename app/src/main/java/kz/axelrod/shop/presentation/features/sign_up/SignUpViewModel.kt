package kz.axelrod.shop.presentation.features.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.axelrod.shop.domain.model.TextInput
import kz.axelrod.shop.domain.model.User
import kz.axelrod.shop.domain.repository.AuthorizationRepository
import kz.axelrod.shop.domain.use_case.ValidateEmail
import kz.axelrod.shop.domain.use_case.ValidateField
import kz.axelrod.shop.presentation.model.ResourceUiState
import kz.axelrod.shop.presentation.model.TextFieldUiState
import kz.axelrod.shop.presentation.model.ValidationResult
import kz.axelrod.shop.presentation.mvi.BaseViewModel

class SignUpViewModel(
    private val validateEmail: ValidateEmail,
    private val validateField: ValidateField,
    private val repository: AuthorizationRepository,

    ) :
    BaseViewModel<SignUpContract.State, SignUpContract.Event, SignUpContract.Effect>() {

    override fun createInitialState(): SignUpContract.State =
        SignUpContract.State(
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
            name = TextFieldUiState(
                input = mutableStateOf(""),
                validationResult = mutableStateOf(ValidationResult(successful = true)),
                onFieldChanged = {
                    validateAllFieldsAndUpdateButtonState(
                        currentState,
                        TextInput.FIRST_NAME
                    )
                }
            ),
            state = ResourceUiState.Idle
        )

    override fun handleEvent(event: SignUpContract.Event) {
        when (event) {
            SignUpContract.Event.OnSignUpClicked -> {
                registerUser(
                    name = state.name.input.value,
                    email = state.email.input.value,
                    password = state.password.input.value
                )
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            setState {
                copy(
                    state = ResourceUiState.Loading
                )
            }
            setEffect {
                SignUpContract.Effect.OnLoading
            }
            delay(2000)

            val userId = repository.loginUser(
                email,
                password
            )
            if (userId != null) {
                setEffect {
                    SignUpContract.Effect.OnUserExist
                }
            } else {
                repository.createUser(
                    User(
                        name = name,
                        email = email,
                        password = password
                    )
                )
                setEffect {
                    SignUpContract.Effect.OnUserCreated
                }
            }
        }
    }

    private fun validateAllFieldsAndUpdateButtonState(
        currentState: SignUpContract.State,
        fieldChanged: TextInput
    ) {
        with(currentState) {
            val validateEmailResult = validateEmail(email.input.value, email.textInput)
            val validatePasswordResult = validateField(
                password.input.value,
                password.textInput
            )
            val validateNameResult = validateField(
                name.input.value,
                name.textInput
            )

            when (fieldChanged) {
                TextInput.EMAIL_ADDRESS -> email.validationResult.value = validateEmailResult
                TextInput.PASSWORD -> password.validationResult.value = validatePasswordResult
                TextInput.FIRST_NAME -> name.validationResult.value = validateNameResult
                else -> {}
            }

            setState {
                copy(
                    isButtonAvailable = validateEmailResult.successful
                            && validatePasswordResult.successful && validateNameResult.successful
                )
            }
        }
    }
}
