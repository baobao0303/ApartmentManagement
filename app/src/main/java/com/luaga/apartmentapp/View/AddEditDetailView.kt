package com.luaga.apartmentapp.View

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luaga.apartmentapp.R
import com.luaga.apartmentapp.components.ApartmentTextField
import com.luaga.apartmentapp.navigation.AppBar.AppBarView
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel
@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: ApartmentViewModel,
    navController: NavController
){
    //LocalContext.current thường được sử dụng để truy cập thông tin
    val context = LocalContext.current;
    Scaffold(
        topBar = {
            AppBarView(
                title = if(id != 0L) stringResource(id = R.string.update_apartment)
                else stringResource(id = R.string.add_apartment),
                onBackNavClicked = {
                    // Provide a function to handle back navigation
                    Toast.makeText(context, "Button CLicked", Toast.LENGTH_LONG).show()
                    navController.navigateUp()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            // Add your content here
            //name = "Alice Smith",
            //contact = "alice.smith@example.com",
            //phone = "555-123-4567",
            //identityCardNumber = "GHI789012",
            //moveInDate = "2023-07-01",
            //leaseExpiryDate = "2024-06-30"
            ApartmentTextField(label = "Name", value =viewModel.apartmentNameState, onValueChanged = {
                viewModel.onApartmentNameChanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            ApartmentTextField(label = "Contact", value =viewModel.apartmentContactState, onValueChanged = {
                viewModel.onApartmentContactChanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            ApartmentTextField(label = "Phone", value =viewModel.apartmentPhoneState, onValueChanged = {
                viewModel.onApartmentPhoneChanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            ApartmentTextField(label = "Identity Card Number", value =viewModel.apartmentIdentityCardNumberState, onValueChanged = {
                viewModel.onApartmentIdentityCardNumberChanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            ApartmentTextField(label = "Move In Date", value =viewModel.apartmentMoveInDateState, onValueChanged = {
                viewModel.onApartmentMoveInDateChanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            ApartmentTextField(label = "Lease Expiry Date", value =viewModel.apartmentLeaseExpiryDateInDateState, onValueChanged = {
                viewModel.onApartmentLeaseExpiryDateInDateChanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(
                    viewModel.apartmentNameState.isNotEmpty() &&
                    viewModel.apartmentContactState.isNotEmpty() &&
                    viewModel.apartmentPhoneState.isNotEmpty() &&
                    viewModel.apartmentIdentityCardNumberState.isNotEmpty() &&
                    viewModel.apartmentMoveInDateState.isNotEmpty() &&
                    viewModel.apartmentLeaseExpiryDateInDateState.isNotEmpty()
                ){
                    // TODO UpdateApartment
                }else{
                    // TODO AddApartment
                }
            }) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_apartment)
                    else stringResource(id = R.string.add_apartment),
                    style =  TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}
