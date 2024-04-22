package com.luaga.apartmentapp.View

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel

@Composable
fun UserView (
    navController: NavController,
    viewModel: ApartmentViewModel
){
    Text(text = "bao")
}