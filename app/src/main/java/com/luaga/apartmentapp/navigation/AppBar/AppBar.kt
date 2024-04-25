package com.luaga.apartmentapp.navigation.AppBar

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luaga.apartmentapp.R
import com.luaga.apartmentapp.ui.theme.Primary
import com.luaga.apartmentapp.ui.theme.TextColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit,
){
    val navigationIcon: (@Composable () -> Unit)? = if (title != "Apartment management") {
        {
            IconButton(onClick = onBackNavClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    } else {
        null
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id =  R.color.TextColors),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp),
                style = TextStyle(fontSize = 20.sp)
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.Primary),
        navigationIcon = navigationIcon
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarViewPreview(){
    AppBarView(
        title = "Apartment management",
        {}
    )
}