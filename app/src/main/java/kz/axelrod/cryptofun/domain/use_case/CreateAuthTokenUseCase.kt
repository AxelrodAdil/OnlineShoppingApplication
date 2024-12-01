package kz.axelrod.cryptofun.domain.use_case

import kz.axelrod.cryptofun.domain.repository.AuthTokenRepository

class CreateAuthTokenUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(token: Int) {
        return authRepository.storeAuthToken(token)
    }
}
