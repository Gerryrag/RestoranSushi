package com.example.restoran.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Menu : Screen("menu")
    object DetailMenu : Screen("detail/{menuId}")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
}
