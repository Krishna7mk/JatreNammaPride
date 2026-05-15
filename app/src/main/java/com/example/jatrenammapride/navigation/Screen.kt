package com.example.jatrenammapride.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Schedule : Screen("schedule")
    object Map : Screen("map")
    object LostFound : Screen("lostfound")
    object Safety : Screen("safety")
    object Ai : Screen("ai")
    object Admin : Screen("admin")
    object AdminPanel : Screen("admin_panel")
    object AdminLostFound : Screen("admin_lostfound")
}
