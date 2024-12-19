package kz.axelrod.shop.data.mapper

import kz.axelrod.shop.data.model.user.UserDBModel
import kz.axelrod.shop.domain.model.User

class AuthorizationMapper {

    fun mapEntityToDBModel(user: User): UserDBModel = UserDBModel(
        id = user.id,
        name = user.name,
        email = user.email,
        password = user.password
    )

    fun mapDBModelToEntity(userDBModel: UserDBModel?): User? {
        return when (userDBModel) {
            null -> null
            else -> User(
                id = userDBModel.id,
                name = userDBModel.name,
                email = userDBModel.email,
                password = userDBModel.password
            )
        }
    }
}
