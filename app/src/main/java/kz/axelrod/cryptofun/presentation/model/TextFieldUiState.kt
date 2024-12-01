package kz.axelrod.cryptofun.presentation.model

import androidx.compose.runtime.MutableState
import kz.axelrod.cryptofun.domain.model.TextInput

data class TextFieldUiState(
    var input: MutableState<String>,
    val textInput: TextInput = TextInput.BASE,
    var validationResult: MutableState<ValidationResult>,
    val onFieldChanged: () -> Unit? = {}
)
