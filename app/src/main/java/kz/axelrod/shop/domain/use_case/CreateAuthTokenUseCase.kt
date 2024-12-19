package kz.axelrod.shop.domain.use_case

import kz.axelrod.shop.domain.repository.AuthTokenRepository

class CreateAuthTokenUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(token: Int) {
        return authRepository.storeAuthToken(token)
    }
}
