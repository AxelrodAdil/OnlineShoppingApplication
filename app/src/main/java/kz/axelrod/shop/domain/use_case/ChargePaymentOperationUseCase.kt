package kz.axelrod.shop.domain.use_case

import kz.axelrod.shop.domain.repository.OnlineShopRepository

class GetCharactersUseCase(
    private val repository: OnlineShopRepository
) {
    suspend operator fun invoke() = repository.getCharacters()
}
