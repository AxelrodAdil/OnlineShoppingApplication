package kz.axelrod.cryptofun.domain.use_case

import kz.axelrod.cryptofun.domain.repository.AuthTokenRepository

class IsUserAuthUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(): Boolean {
        return authRepository.isUserAuth()
    }
}