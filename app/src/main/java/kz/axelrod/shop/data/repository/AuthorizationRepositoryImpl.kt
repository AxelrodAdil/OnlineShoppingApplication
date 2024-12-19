package kz.axelrod.shop.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.axelrod.shop.data.local.AuthorizationDao
import kz.axelrod.shop.data.mapper.AuthorizationMapper
import kz.axelrod.shop.domain.model.User
import kz.axelrod.shop.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl(
    private val authorizationDao: AuthorizationDao,
    private val mapper: AuthorizationMapper
) : AuthorizationRepository {

    override suspend fun checkUser(email: String): Boolean =
        withContext(Dispatchers.IO) {
            authorizationDao.checkUserByEmail(email)
        }

    override suspend fun createUser(user: User) {
        withContext(Dispatchers.IO) {
            authorizationDao.createUser(mapper.mapEntityToDBModel(user))
        }
    }

    override suspend fun loginUser(email: String, password: String): Int? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.loginUser(email, password))?.id
        }

    override suspend fun getUserByTokenId(id: Int): User? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.getUserById(id))
        }
}