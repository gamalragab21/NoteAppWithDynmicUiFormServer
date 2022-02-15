package com.developers.todoappwithcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developers.todoappwithcompose.ui.screens.EditProfileScreen
import com.developers.todoappwithcompose.ui.screens.HomeScreen
import com.developers.todoappwithcompose.ui.screens.SplashScreen


@Composable
fun MyAppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MyNavigationScreens.SplashScreen.route
    ) {
        composable(MyNavigationScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(MyNavigationScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(MyNavigationScreens.EditProfileScreen.route) {
            EditProfileScreen(navController)
        }
    }
}