package kz.axelrod.cryptofun.presentation.model

import kz.axelrod.cryptofun.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)
