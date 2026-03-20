package com.example.shopapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopapp.ui.screen.ProductDetailScreen
import com.example.shopapp.ui.screen.ProductListScreen
import com.example.shopapp.viewmodel.ProductViewModel

sealed class Screen(val route: String) {
    object ProductList : Screen("product_list")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.ProductList.route) {

        composable(Screen.ProductList.route) {
            ProductListScreen(
                viewModel = viewModel,
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            ProductDetailScreen(
                productId = productId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
