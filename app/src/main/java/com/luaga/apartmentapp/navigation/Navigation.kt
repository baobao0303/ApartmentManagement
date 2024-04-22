package com.luaga.apartmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luaga.apartmentapp.View.AddEditDetailView
import com.luaga.apartmentapp.View.HomeView

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
        composable(Screen.AddScreen.route){
            AddEditDetailView(
                id = 0L,
                viewModel= viewModel,
                navController = navController
            )
        }
    }
}