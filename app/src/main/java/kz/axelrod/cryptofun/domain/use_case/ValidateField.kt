package kz.axelrod.cryptofun.domain.use_case

import kz.axelrod.cryptofun.domain.model.TextInput
import kz.axelrod.cryptofun.presentation.model.ValidationResult
import kz.axelrod.cryptofun.utils.UiText

class ValidateField {

    operator fun invoke(text: String, textInput: TextInput): ValidationResult {
        if (text.isBlank())
            return ValidationResult(
                successful = false,
                errorMessage = UiText.DynamicString("Field is required"),
            )
        return ValidationResult(successful = true)
    }
}
