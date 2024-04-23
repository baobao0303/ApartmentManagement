package com.luaga.apartmentapp.View

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luaga.apartmentapp.R
import com.luaga.apartmentapp.components.ApartmentTextField
import com.luaga.apartmentapp.data.Apartment
import com.luaga.apartmentapp.navigation.AppBar.AppBarView
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: ApartmentViewModel,
    navController: NavController
){

    // Create snackBars
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L){
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
        ))
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
    }else{
        viewModel.apartmentNumberState = ""
        viewModel.floorState = 0
        viewModel.ownerIdState = 0
        viewModel.numBedroomsState = 0
        viewModel.numBathroomsState = 0
        viewModel.areaSqftState = 0
        viewModel.rentState = 0
        viewModel.internetFeeState = 0
        viewModel.garbageFeeState = 0
        viewModel.statusState = ""
        viewModel.waterUsageCurrentState = 0
        viewModel.bedsQuantityState = 0
        viewModel.bedsDamagedState = 0
        viewModel.sofasQuantityState = 0
        viewModel.sofasDamagedState = 0
        viewModel.tablesQuantityState = 0
        viewModel.tablesDamagedState = 0
        viewModel.chairsQuantityState = 0
        viewModel.chairsDamagedState = 0
        viewModel.appliancesQuantityState = 0
        viewModel.appliancesDamagedState = 0
        viewModel.gymState = false
        viewModel.swimmingPoolState = false
        viewModel.laundryState = false
        viewModel.parkingState = false
    }

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
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = "Thông tin Căn hộ",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(vertical = 16.dp) // Thêm padding top và bottom
                    )
                    ApartmentTextField(
                        label = "Số phòng của căn hộ trong toà nhà",
                        value = viewModel.apartmentNumberState,
                        onValueChanged = viewModel::onApartmentNumberChanged
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Căn hộ ở tầng bao nhiêu",
                        value = viewModel.floorState.toString(),
                        onValueChanged = { viewModel.onFloorChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số phòng ngủ trong căn hộ",
                        value = viewModel.numBedroomsState.toString(),
                        onValueChanged = { viewModel.onNumBedroomsChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số phòng tắm trong căn hộ",
                        value = viewModel.numBathroomsState.toString(),
                        onValueChanged = { viewModel.onNumBathroomsChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Diện tích căn hộ (m^2)",
                        value = viewModel.areaSqftState.toString(),
                        onValueChanged = { viewModel.onAreaSqftChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Giá tiền thuê căn hộ",
                        value = viewModel.rentState.toString(),
                        onValueChanged = { viewModel.onRentChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Giá tiền Internet",
                        value = viewModel.internetFeeState.toString(),
                        onValueChanged = { viewModel.onInternetFeeChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Giá tiền rác",
                        value = viewModel.garbageFeeState.toString(),
                        onValueChanged = { viewModel.onGarbageFeeChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ApartmentTextField(
                        label = "Trạng thái cho thuê",
                        value = viewModel.statusState,
                        onValueChanged = viewModel::onStatusChanged
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ApartmentTextField(
                        label = "Số khối nước hiện tại",
                        value = viewModel.waterUsageCurrentState.toString(),
                        onValueChanged = { viewModel.onWaterUsageCurrentChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Nội thất",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(vertical = 16.dp) // Thêm padding top và bottom
                    )
                    ApartmentTextField(
                        label = "Số lượng giường",
                        value = viewModel.bedsQuantityState.toString(),
                        onValueChanged = { viewModel.onBedsQuantityChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng giường hỏng",
                        value = viewModel.bedsDamagedState.toString(),
                        onValueChanged = { viewModel.onBedsDamagedChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng sofa",
                        value = viewModel.sofasQuantityState.toString(),
                        onValueChanged = { viewModel.onSofasQuantityChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng sofa hỏng",
                        value = viewModel.sofasDamagedState.toString(),
                        onValueChanged = { viewModel.onSofasDamagedChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng bàn",
                        value = viewModel.tablesQuantityState.toString(),
                        onValueChanged = { viewModel.onTablesQuantityChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng bàn hỏng",
                        value = viewModel.tablesDamagedState.toString(),
                        onValueChanged = { viewModel.onTablesDamagedChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng ghế",
                        value = viewModel.chairsQuantityState.toString(),
                        onValueChanged = { viewModel.onChairsQuantityChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng ghế hỏng",
                        value = viewModel.chairsDamagedState.toString(),
                        onValueChanged = { viewModel.onChairsDamagedChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng thiết bị điện tử",
                        value = viewModel.appliancesQuantityState.toString(),
                        onValueChanged = { viewModel.onAppliancesQuantityChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ApartmentTextField(
                        label = "Số lượng thiết bị điện tử hỏng",
                        value = viewModel.appliancesDamagedState.toString(),
                        onValueChanged = { viewModel.onAppliancesDamagedChanged(it.toIntOrNull() ?: 0) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Dịch vụ cho căn hộ",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(vertical = 16.dp) // Thêm padding top và bottom
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.gymState,
                            onCheckedChange = viewModel::onGymChanged
                        )
                        Text("Có phòng gym")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.swimmingPoolState,
                            onCheckedChange = viewModel::onSwimmingPoolChanged
                        )
                        Text("Có bể bơi")
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.laundryState,
                            onCheckedChange = viewModel::onLaundryChanged
                        )
                        Text("Có dịch vụ giặt là")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.parkingState,
                            onCheckedChange = viewModel::onParkingChanged
                        )
                        Text("Có phòng gym")
                    }

                    Button(
                        modifier = Modifier
                            .padding(vertical = 16.dp) // Thêm padding ở phía trên và dưới
                            .fillMaxWidth() // Đảm bảo nút sử dụng toàn bộ chiều rộng của parent
                            .align(Alignment.CenterHorizontally), // Căn giữa theo chiều ngang
                        onClick = {
                        // Gọi hàm buildApartmentInfo() và lưu thông tin apartment vào biến apartmentInfo
                        val apartmentInfo = buildApartmentInfo(viewModel)

                        // Log thông tin apartment
                        Log.d("ApartmentInfo", apartmentInfo)
                        if(
                            viewModel.apartmentNumberState.isNotEmpty() &&
                            viewModel.statusState.isNotEmpty()
                        ){
                            if(id != 0L){
                                // TODO UpdateApartment
                                viewModel.updateApartment(
                                    Apartment(
                                        id = id,
                                        apartment_number = viewModel.apartmentNumberState.trim(),
                                        floor = viewModel.floorState,
                                        owner_id = viewModel.ownerIdState,
                                        num_bedrooms = viewModel.numBedroomsState,
                                        num_bathrooms = viewModel.numBathroomsState,
                                        area_sqft = viewModel.areaSqftState,
                                        rent = viewModel.rentState,
                                        internet_fee = viewModel.internetFeeState,
                                        garbage_fee = viewModel.garbageFeeState,
                                        status = viewModel.statusState.trim(),
                                        water_usage_current = viewModel.waterUsageCurrentState,
                                        beds_quantity = viewModel.bedsQuantityState,
                                        beds_damaged = viewModel.bedsDamagedState,
                                        sofas_quantity = viewModel.sofasQuantityState,
                                        sofas_damaged = viewModel.sofasDamagedState,
                                        tables_quantity = viewModel.tablesQuantityState,
                                        tables_damaged = viewModel.tablesDamagedState,
                                        chairs_quantity = viewModel.chairsQuantityState,
                                        chairs_damaged = viewModel.chairsDamagedState,
                                        appliances_quantity = viewModel.appliancesQuantityState,
                                        appliances_damaged = viewModel.appliancesDamagedState,
                                        gym = viewModel.gymState,
                                        swimming_pool = viewModel.swimmingPoolState,
                                        laundry = viewModel.laundryState,
                                        parking = true //viewModel.parkingState
                                    )
                                )
                            }else{
                                // TODO AddApartment
                                viewModel.addApartment(
                                    Apartment(
                                        apartment_number = viewModel.apartmentNumberState.trim(),
                                        floor = viewModel.floorState,
                                        owner_id = viewModel.ownerIdState,
                                        num_bedrooms = viewModel.numBedroomsState,
                                        num_bathrooms = viewModel.numBathroomsState,
                                        area_sqft = viewModel.areaSqftState,
                                        rent = viewModel.rentState,
                                        internet_fee = viewModel.internetFeeState,
                                        garbage_fee = viewModel.garbageFeeState,
                                        status = viewModel.statusState.trim(),
                                        water_usage_current = viewModel.waterUsageCurrentState,
                                        beds_quantity = viewModel.bedsQuantityState,
                                        beds_damaged = viewModel.bedsDamagedState,
                                        sofas_quantity = viewModel.sofasQuantityState,
                                        sofas_damaged = viewModel.sofasDamagedState,
                                        tables_quantity = viewModel.tablesQuantityState,
                                        tables_damaged = viewModel.tablesDamagedState,
                                        chairs_quantity = viewModel.chairsQuantityState,
                                        chairs_damaged = viewModel.chairsDamagedState,
                                        appliances_quantity = viewModel.appliancesQuantityState,
                                        appliances_damaged = viewModel.appliancesDamagedState,
                                        gym = viewModel.gymState,
                                        swimming_pool = viewModel.swimmingPoolState,
                                        laundry = viewModel.laundryState,
                                        parking = true //viewModel.parkingState
                                    )
                                )
                                snackMessage.value = "Apartment has been created"
                            }

                        }else{
                            // Enter field for apartment to be created
                            snackMessage.value = "Enter fields to create a apartment"
                        }
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                            navController.navigateUp()
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
    }
}
// Trong hàm buildApartmentInfo(), thêm đoạn code sau để log thông tin apartment
private fun buildApartmentInfo(viewModel: ApartmentViewModel): String {
    val apartmentInfo = "apartment_number = ${viewModel.apartmentNumberState.trim()}, " +
            "floor = ${viewModel.floorState}, " +
            "owner_id = ${viewModel.ownerIdState}, " +
            "num_bedrooms = ${viewModel.numBedroomsState}, " +
            "num_bathrooms = ${viewModel.numBathroomsState}, " +
            "area_sqft = ${viewModel.areaSqftState}, " +
            "rent = ${viewModel.rentState}, " +
            "internet_fee = ${viewModel.internetFeeState}, " +
            "garbage_fee = ${viewModel.garbageFeeState}, " +
            "status = ${viewModel.statusState.trim()}, " +
            "water_usage_current = ${viewModel.waterUsageCurrentState}, " +
            "beds_quantity = ${viewModel.bedsQuantityState}, " +
            "beds_damaged = ${viewModel.bedsDamagedState}, " +
            "sofas_quantity = ${viewModel.sofasQuantityState}, " +
            "sofas_damaged = ${viewModel.sofasDamagedState}, " +
            "tables_quantity = ${viewModel.tablesQuantityState}, " +
            "tables_damaged = ${viewModel.tablesDamagedState}, " +
            "chairs_quantity = ${viewModel.chairsQuantityState}, " +
            "chairs_damaged = ${viewModel.chairsDamagedState}, " +
            "appliances_quantity = ${viewModel.appliancesQuantityState}, " +
            "appliances_damaged = ${viewModel.appliancesDamagedState}, " +
            "gym = ${viewModel.gymState}, " +
            "swimming_pool = ${viewModel.swimmingPoolState}, " +
            "laundry = ${viewModel.laundryState}, " +
            "parking = ${viewModel.parkingState}"

    // Log thông tin apartment
    Log.d("ApartmentInfo", apartmentInfo)

    return apartmentInfo
}
