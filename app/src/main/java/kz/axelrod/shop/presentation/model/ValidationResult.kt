package kz.axelrod.shop.presentation.model

import kz.axelrod.shop.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)
