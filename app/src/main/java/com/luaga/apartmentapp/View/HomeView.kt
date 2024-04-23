package com.luaga.apartmentapp.View

import ApartmentItem
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luaga.apartmentapp.data.DummyApartments
import com.luaga.apartmentapp.navigation.AppBar.AppBarView
import com.luaga.apartmentapp.navigation.Screen
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel

@OptIn(ExperimentalMaterialApi::class)
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
                    navController.navigate(Screen.AddScreen.route + "/0L")
//                    Toast.makeText(context, "Floating Action Button CLicked", Toast.LENGTH_LONG).show()
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        }
    ) {
        val apartmentList = viewModel.getAllApartments.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(
                //DummyApartments.apartments
                apartmentList.value
            ) { apartment ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd) {
                            // Xóa căn hộ nếu vuốt từ phải sang trái
                            viewModel.deleteApartment(apartment)
                        } else if (it == DismissValue.DismissedToStart) {
                            // Tạo route mới nếu vuốt từ trái sang phải
                            val id = apartment.id
                            navController.navigate("${Screen.UserScreen.route}/$id")
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection
                                == DismissDirection.EndToStart) Color.Red else Color.Transparent
                            ,label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ){
                            androidx.compose.material.Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White
                            )
                        }

                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {FractionalThreshold(1f)},
                    dismissContent = {
                        ApartmentItem(apartment = apartment,
                            onClick = {
                                val id = apartment.id
                                navController.navigate(Screen.AddScreen.route + "/$id")
                            },
                            onUserButtonClick = {
                                val id = apartment.id
                                navController.navigate("${Screen.UserScreen.route}/$id")
                            }
                        )
                    }
                )

            }
        }
    }
}
