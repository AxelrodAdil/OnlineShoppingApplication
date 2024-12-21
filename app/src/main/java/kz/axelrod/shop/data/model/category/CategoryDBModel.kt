package kz.axelrod.shop.data.model.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.axelrod.shop.utils.Constants.CATEGORY

@Entity(
    tableName = CATEGORY
)
data class CategoryDBModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val categoryName: String
)
