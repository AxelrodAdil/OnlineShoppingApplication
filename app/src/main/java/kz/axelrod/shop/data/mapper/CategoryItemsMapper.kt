package kz.axelrod.shop.data.mapper

import kz.axelrod.shop.data.model.category.CategoryListApiModel
import kz.axelrod.shop.domain.mapper.Mapper
import kz.axelrod.shop.domain.model.characters.CategoryItem

class CategoryItemsMapper : Mapper<CategoryListApiModel, List<CategoryItem>>() {

    override fun mapData(from: CategoryListApiModel): List<CategoryItem> {
        return from.categories?.map {
            CategoryItem(
                categoryId = it.categoryId ?: 0,
                categoryName = it.categoryName ?: ""
            )
        }.orEmpty()
    }
}
