package kz.axelrod.shop.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.axelrod.shop.presentation.features.catalog.CatalogScreen
import kz.axelrod.shop.presentation.features.home.HomeScreen
import kz.axelrod.shop.presentation.features.sign_in.SignInScreen
import kz.axelrod.shop.presentation.features.sign_up.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SignUp.route) {
        composable(Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Catalog.route) {
            CatalogScreen(navController = navController)
        }
    }
}
