package com.luaga.apartmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luaga.apartmentapp.View.AddEditDetailView
import com.luaga.apartmentapp.View.HomeView
import com.luaga.apartmentapp.View.UserView

@Composable
fun Navigation(
    viewModel: ApartmentViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route){
            HomeView(navController,viewModel)
        }
        composable(Screen.UserScreen.route){
            UserView(navController,viewModel)
        }
        composable(Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){entry->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel , navController = navController)
        }
    }
}