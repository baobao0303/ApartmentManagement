package com.luaga.apartmentapp.View

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luaga.apartmentapp.R
import com.luaga.apartmentapp.data.Apartment
import com.luaga.apartmentapp.navigation.AppBar.AppBarView
import com.luaga.apartmentapp.navigation.Screen
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel

@Composable
fun UserView(
    navController: NavController,
    viewModel: ApartmentViewModel,
    id: Long // Thêm tham số ID
) {
        val context = LocalContext.current
        val apartment = viewModel.getAApartmentById(id).collectAsState(initial = Apartment(
            0L,
            "",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            "",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            false,
            false,
            false,
            false,
        )
        )
        viewModel.apartmentNumberState  = apartment.value.apartment_number
        viewModel.floorState = apartment.value.floor
        viewModel.ownerIdState = apartment.value.owner_id
        viewModel.numBedroomsState = apartment.value.num_bedrooms
        viewModel.numBathroomsState = apartment.value.num_bathrooms
        viewModel.areaSqftState = apartment.value.area_sqft
        viewModel.rentState = apartment.value.rent
        viewModel.internetFeeState = apartment.value.internet_fee
        viewModel.garbageFeeState = apartment.value.garbage_fee
        viewModel.statusState = apartment.value.status
        viewModel.waterUsageCurrentState =apartment.value.water_usage_current ?: 0
        viewModel.bedsQuantityState = apartment.value.beds_quantity
        viewModel.bedsDamagedState = apartment.value.beds_damaged
        viewModel.sofasQuantityState = apartment.value.sofas_quantity
        viewModel.sofasDamagedState = apartment.value.sofas_damaged
        viewModel.tablesQuantityState = apartment.value.tables_quantity
        viewModel.tablesDamagedState = apartment.value.tables_damaged
        viewModel.chairsQuantityState = apartment.value.chairs_quantity
        viewModel.chairsDamagedState = apartment.value.chairs_damaged
        viewModel.appliancesQuantityState = apartment.value.appliances_quantity
        viewModel.appliancesDamagedState = apartment.value.appliances_damaged
        viewModel.gymState = apartment.value.gym
        viewModel.swimmingPoolState = apartment.value.swimming_pool
        viewModel.laundryState = apartment.value.laundry
        viewModel.parkingState = apartment.value.parking

    Log.d("ApartmentInfo1",apartment.value.toString())

    Scaffold(
        topBar = {
            AppBarView(
                title = "Apartment renter",
                onBackNavClicked = {
                    navController.navigate(route = "home_screen")
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Nội dung của khung
                Text(text = "Khung nội dung", color = Color.Black)
            }

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // Xử lý khi nút được nhấn
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f) // Thiết lập trọng số để các nút có kích thước bằng nhau
                ) {
                    Text(
                        text = "Thêm thông tin người thuê",
                        textAlign = TextAlign.Center // Canh chữ vào giữa của nút
                    )
                }

                Button(
                    onClick = {
                        val totalAmount = calculateMonthlyRent(apartment.value)
                        // In ra tổng số tiền
                        Log.d("MonthlyRent", "Tổng số tiền hàng tháng: $totalAmount")
                        Toast.makeText(context, "Tổng số tiền hàng tháng: $totalAmount", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f) // Thiết lập trọng số để các nút có kích thước bằng nhau
                ) {
                    Text(
                        text = "In biên lai thuê hàng tháng",
                        textAlign = TextAlign.Center // Canh chữ vào giữa của nút
                    )
                }
            }
        }
    }
}
fun calculateMonthlyRent(apartment: Apartment): Double {
    val rent = apartment.rent
    val internetFee = apartment.internet_fee
    val garbageFee = apartment.garbage_fee
    val waterUsageCurrent = apartment.water_usage_current ?: 0

    // Tính tổng số tiền
    val totalAmount = rent + internetFee + garbageFee + waterUsageCurrent

    return totalAmount.toDouble()
}

