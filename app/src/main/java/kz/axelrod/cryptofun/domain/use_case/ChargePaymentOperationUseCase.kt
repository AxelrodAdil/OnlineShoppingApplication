package kz.axelrod.cryptofun.domain.use_case

import kz.axelrod.cryptofun.domain.repository.OnlineCryptoStoreRepository

class GetCharactersUseCase(
    private val repository: OnlineCryptoStoreRepository
) {
    suspend operator fun invoke() = repository.getCharacters()
}
