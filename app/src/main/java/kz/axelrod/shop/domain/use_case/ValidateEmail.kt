package kz.axelrod.shop.domain.use_case

import kz.axelrod.shop.domain.model.TextInput
import kz.axelrod.shop.presentation.model.ValidationResult
import kz.axelrod.shop.utils.UiText

class ValidateEmail {

    operator fun invoke(email: String, emailAddress: TextInput): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.DynamicString("Email is Blank")
            )
        }
        if (!email.matches(emailRegex.toRegex())) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.DynamicString("Invalid email")
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    companion object {
        const val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"

    }
}
