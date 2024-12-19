package kz.axelrod.shop.data.mapper

import kz.axelrod.shop.data.model.products.ProductListApiModel
import kz.axelrod.shop.domain.mapper.Mapper
import kz.axelrod.shop.domain.model.characters.ProductItem

class ProductItemsMapper : Mapper<ProductListApiModel, List<ProductItem>>() {

    // Map ProductListApiModel to a List of ProductItem
    override fun mapData(from: ProductListApiModel): List<ProductItem> {
        return from.products?.map {
            ProductItem(
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
