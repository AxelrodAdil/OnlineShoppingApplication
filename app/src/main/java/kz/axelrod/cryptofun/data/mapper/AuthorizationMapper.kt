package kz.axelrod.cryptofun.data.mapper

import kz.axelrod.cryptofun.data.model.user.UserDBModel
import kz.axelrod.cryptofun.domain.model.User

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
