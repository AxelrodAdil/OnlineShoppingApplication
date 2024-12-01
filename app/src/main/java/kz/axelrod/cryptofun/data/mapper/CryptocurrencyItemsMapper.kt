package kz.axelrod.cryptofun.data.mapper

import kz.axelrod.cryptofun.data.model.products.CryptocurrencyListApiModel
import kz.axelrod.cryptofun.domain.mapper.Mapper
import kz.axelrod.cryptofun.domain.model.characters.CryptocurrencyItem

class CryptocurrencyItemsMapper : Mapper<CryptocurrencyListApiModel, List<CryptocurrencyItem>>() {

    // Map CryptocurrencyListApiModel to a List of CryptocurrencyItem
    override fun mapData(from: CryptocurrencyListApiModel): List<CryptocurrencyItem> {
        return from.products?.map {
            CryptocurrencyItem(
                productId = it.productId ?: 0,
                name = it.name ?: "",
                description = it.description ?: "",
                price = it.price ?: 0.0,
                imageUrl = it.imageUrl ?: "",
                category = it.category ?: ""
            )
        }.orEmpty()
    }
}
