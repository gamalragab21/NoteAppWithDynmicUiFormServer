package com.developers.todoappwithcompose.navigation

sealed class MyNavigationScreens(val route:String) {
    object SplashScreen: MyNavigationScreens("splash_route")
    object HomeScreen: MyNavigationScreens("home_route")
    object EditProfileScreen: MyNavigationScreens("edit_route")
}