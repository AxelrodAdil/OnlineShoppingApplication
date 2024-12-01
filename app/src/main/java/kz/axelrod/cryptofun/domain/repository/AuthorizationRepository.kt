package kz.axelrod.cryptofun.domain.repository

import kz.axelrod.cryptofun.domain.model.User

interface AuthorizationRepository {

    suspend fun checkUser(email: String): Boolean
    suspend fun createUser(user: User)
    suspend fun loginUser(email: String, password: String): Int?
    suspend fun getUserByTokenId(id: Int): User?
}