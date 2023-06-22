package com.example.socialcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About: Screen("about")
    object Detail : Screen("home/{socialId}") {
        fun createRoute(socialId: Int) = "home/$socialId"
    }
}