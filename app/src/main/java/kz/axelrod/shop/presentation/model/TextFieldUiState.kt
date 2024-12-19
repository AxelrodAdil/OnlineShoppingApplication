package kz.axelrod.shop.presentation.model

import androidx.compose.runtime.MutableState
import kz.axelrod.shop.domain.model.TextInput

data class TextFieldUiState(
    var input: MutableState<String>,
    val textInput: TextInput = TextInput.BASE,
    var validationResult: MutableState<ValidationResult>,
    val onFieldChanged: () -> Unit? = {}
)
