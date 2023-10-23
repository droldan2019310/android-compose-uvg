package com.zezzi.eventzezziapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zezzi.eventzezziapp.ui.meals.view.MealsCategoriesScreen
import com.zezzi.eventzezziapp.ui.meals.view.MealsDetailsScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationState.Meals.route,
        modifier = modifier
    ) {
        composable(route = NavigationState.Meals.route) {
            MealsCategoriesScreen(navController)
        }

        composable(
            route = "${NavigationState.Meals.route}/{mealName}",
            arguments = listOf(navArgument("mealName") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealName = backStackEntry.arguments?.getString("mealName")
            mealName?.let {
                MealsDetailsScreen(navController, it)
            }
        }

    }
}