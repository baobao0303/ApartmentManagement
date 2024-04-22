package com.luaga.apartmentapp.navigation

sealed class  Screen (val route : String){
    object HomeScreen: Screen(route = "home_screen")
    object AddScreen: Screen(route = "add_screen")
    object UserScreen: Screen(route = "user_screen")
}