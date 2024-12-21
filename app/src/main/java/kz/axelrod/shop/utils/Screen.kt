package kz.axelrod.shop.utils

sealed class Screen(val route: String) {
    data object SignUp : Screen("nav_sign_up")
    data object SignIn : Screen("nav_sign_in")
    data object Home : Screen("nav_home")
    data object Catalog : Screen("nav_catalog")

    companion object {
        const val CITY_ITEM = "cityItem"
    }
}
