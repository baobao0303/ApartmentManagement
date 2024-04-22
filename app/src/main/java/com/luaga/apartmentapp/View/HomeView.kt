package com.luaga.apartmentapp.View

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.luaga.apartmentapp.navigation.AppBar.AppBarView

@Composable
fun HomeView (){
    //LocalContext.current thường được sử dụng để truy cập thông tin
    val context = LocalContext.current;
    Scaffold(
        topBar = {
            AppBarView(
                title = "Apartment management"
            ) {
                Toast.makeText(context, "Button CLicked", Toast.LENGTH_LONG).show()
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(it)) {

        }

    }
}