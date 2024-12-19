package kz.axelrod.shop.domain.use_case

import kz.axelrod.shop.domain.repository.AuthTokenRepository

class IsUserAuthUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(): Boolean {
        return authRepository.isUserAuth()
    }
}