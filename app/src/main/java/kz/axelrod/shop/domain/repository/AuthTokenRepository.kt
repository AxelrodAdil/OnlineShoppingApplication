package kz.axelrod.shop.domain.repository

interface AuthTokenRepository {

    fun getAuthToken(): Int
    fun storeAuthToken(token: Int)
    fun clearAuthToken()
    fun isUserAuth(): Boolean
}
