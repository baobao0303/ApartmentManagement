package com.luaga.apartmentapp.View

import ApartmentItem
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luaga.apartmentapp.data.DummyApartments
import com.luaga.apartmentapp.navigation.AppBar.AppBarView
import com.luaga.apartmentapp.navigation.Screen
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel

@Composable
fun HomeView (
    navController: NavController,
    viewModel: ApartmentViewModel
){
    //LocalContext.current thường được sử dụng để truy cập thông tin
    val context = LocalContext.current;
    Scaffold(
        topBar = {
            AppBarView(
                title = "Apartment management"
            ) {
                Toast.makeText(context, "Button CLicked", Toast.LENGTH_LONG).show()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                containerColor = Color.Black,
                onClick = {
                    /*TODO Add Navigation to add screen*/
                    navController.navigate(Screen.AddScreen.route)
//                    Toast.makeText(context, "Floating Action Button CLicked", Toast.LENGTH_LONG).show()
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(DummyApartments.apartments) { apartment ->
//                if (apartment.ownerId == 1) {
                    ApartmentItem(apartment = apartment) {
                        // Handle item click here if needed
                    }
//                }
            }
        }
    }
}