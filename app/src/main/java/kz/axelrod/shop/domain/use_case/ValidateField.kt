package kz.axelrod.shop.domain.use_case

import kz.axelrod.shop.domain.model.TextInput
import kz.axelrod.shop.presentation.model.ValidationResult
import kz.axelrod.shop.utils.UiText

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
