package com.luaga.apartmentapp.View

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luaga.apartmentapp.data.Apartment
import com.luaga.apartmentapp.data.Tenant
import com.luaga.apartmentapp.navigation.AppBar.AppBarView
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel
import java.text.DecimalFormat
fun formatCurrency(amount: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount)
}

fun formatCurrency(amount: Double): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount)
}

@Composable
fun UserView(
    navController: NavController,
    viewModel: ApartmentViewModel,
    id: Long // Thêm tham số ID
) {
        // Store infor renter
        var tenantName by remember { mutableStateOf("") }
        var tenantEmail by remember { mutableStateOf("") }
        var tenantPhone by remember { mutableStateOf("") }
        var idCardNumber by remember { mutableStateOf("") }
        var leaseStartDate by remember { mutableStateOf("") }
        var leaseEndDate by remember { mutableStateOf("") }
        var permanentAddress by remember { mutableStateOf("") }
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
    var showReceiptDialog by remember { mutableStateOf(false) }
    // Check input infor user is display on screen ?
    var showAddTenantDialog by remember { mutableStateOf(false) }
    var tenantInfo by remember { mutableStateOf(Tenant("", "", "", "", "", "", "")) }

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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Thông tin người thuê", color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tên: ${tenantInfo.name}")
                    Text("Email: ${tenantInfo.email}")
                    Text("Địa chỉ thường trú: ${tenantInfo.permanentAddress}")
                    Text("Số điện thoại người thuê: ${tenantInfo.phone}")
                    Text("Số thẻ căn cước: ${tenantInfo.idCardNumber}" )
                    Text("Ngày thuê căn hộ: ${tenantInfo.leaseStartDate}")
                    Text("Ngày hết hạn hợp đồng: ${tenantInfo.leaseEndDate}")
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { showAddTenantDialog = true },
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Thêm thông tin người thuê",
                        textAlign = TextAlign.Center
                    )
                }
                // AlertDialog để input thông tin người thuê
                if (showAddTenantDialog) {
                    AlertDialog(
                        modifier = Modifier.fillMaxWidth(),
                        onDismissRequest = { showAddTenantDialog = false },
                        title = {
                            Text(
                                "Thông tin người thuê",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                ),
                                modifier = Modifier.fillMaxWidth()
                        )},
                        text = {
                            Column(
                            ) {
                                // TextField để nhập tên người thuê
                                TextField(
                                    value = tenantName,
                                    onValueChange = { tenantName = it },
                                    label = { Text("Tên người thuê") }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                // TextField để nhập email người thuê
                                TextField(
                                    value = tenantEmail,
                                    onValueChange = { tenantEmail = it },
                                    label = { Text("Email người thuê") }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                // TextField để nhập địa chỉ thường trú
                                TextField(
                                    value = permanentAddress,
                                    onValueChange = { permanentAddress = it },
                                    label = { Text("Địa chỉ thường trú") }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                // TextField để nhập số điện thoại người thuê
                                TextField(
                                    value = tenantPhone,
                                    onValueChange = { tenantPhone = it },
                                    label = { Text("Số điện thoại người thuê") }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                // TextField để nhập số thẻ căn cước
                                TextField(
                                    value = idCardNumber,
                                    onValueChange = { idCardNumber = it },
                                    label = { Text("Số thẻ căn cước") }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                // TextField để nhập ngày thuê căn hộ
                                TextField(
                                    value = leaseStartDate,
                                    onValueChange = { leaseStartDate = it },
                                    label = { Text("Ngày thuê căn hộ") }
                                )
                                // TextField để nhập ngày hết hạn hợp đồng
                                TextField(
                                    value = leaseEndDate,
                                    onValueChange = { leaseEndDate = it },
                                    label = { Text("Ngày hết hạn hợp đồng") }
                                )

                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    if (tenantName.isNotBlank() && tenantEmail.isNotBlank()) {
                                        // Gán giá trị của các biến vào đối tượng Tenant
                                        tenantInfo = Tenant(
                                            name = tenantName,
                                            email = tenantEmail,
                                            phone = tenantPhone, // Thêm các trường dữ liệu khác nếu cần
                                            idCardNumber = idCardNumber,
                                            leaseStartDate = leaseStartDate,
                                            leaseEndDate = leaseEndDate,
                                            permanentAddress = permanentAddress
                                        )
                                        // Đóng AlertDialog
                                        showAddTenantDialog = false
                                    } else {
                                        // Hiển thị thông báo lỗi
                                        Toast.makeText(context, "Vui lòng điền đầy đủ thông tin người thuê", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) {
                                Text("Xác nhận")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showAddTenantDialog = false }
                            ) {
                                Text("Hủy")
                            }
                        }
                    )
                }


                // Hộp thông tin biên lai
                if (showReceiptDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            // Ẩn hộp thông tin biên lai khi người dùng nhấn nút "Đóng"
                            showReceiptDialog = false
                        },
                        title = {
                            Text(
                                "Biên lai thuê hàng tháng",
                                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center // Căn giữa tiêu đề
                            )
                        },

                        text = {
                            Column {
                                Text(
                                    text = "Tiền thuê: ${formatCurrency(apartment.value.rent)}",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "Phí internet: ${formatCurrency(apartment.value.internet_fee)}",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "Phí rác: ${formatCurrency(apartment.value.garbage_fee)}",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )
                                val waterUsageCurrent = apartment.value.water_usage_current ?: 0
                                Text(
                                    text = "Tiêu thụ nước: ${formatCurrency(waterUsageCurrent)}",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )

                                AdditionalInfoBox(apartment.value) // Hiển thị các thông tin bổ sung

                                val totalAmount = calculateMonthlyRent(apartment.value)
                                Text(
                                    text = "Tổng số tiền: ${formatCurrency(totalAmount)}",
                                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showReceiptDialog = false
                                }
                            ) {
                                Text("Đóng")
                            }
                        }
                    )
                }
                Button(
                    onClick = {
                        val totalAmount = calculateMonthlyRent(apartment.value)
                        showReceiptDialog = true
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


@Composable
fun AdditionalInfoBox(apartment: Apartment) {
    if (apartment.gym || apartment.swimming_pool || apartment.laundry || apartment.parking) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp) // Margin ở cả hai phía
                .height(150.dp) // Chiều cao của hộp thông tin
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(8.dp) // Margin cho nội dung bên trong
            ) {
                Text(
                    text = "Các dịch vụ sử dụng",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold), // Đặt in đậm
                    modifier = Modifier.padding(bottom = 4.dp, start = 8.dp) // Khoảng cách dưới và lùi vào bên trái ít hơn
                )
                if (apartment.gym) {
                    ListItemText(text = "Có phòng tập gym")
                }
                if (apartment.swimming_pool) {
                    ListItemText(text = "Có hồ bơi")
                }
                if (apartment.laundry) {
                    ListItemText(text = "Có dịch vụ giặt ủi")
                }
                if (apartment.parking) {
                    ListItemText(text = "Có chỗ đậu xe")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Giá: ",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = calculatePrice(apartment).toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    )

                }

            }

        }
    }
}

@Composable
fun ListItemText(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

// Hàm tính giá
fun calculatePrice(apartment: Apartment): String {
    var price = 0
    if (apartment.gym) {
        price += 100000
    }
    if (apartment.swimming_pool) {
        price += 100000
    }
    if (apartment.laundry) {
        price += 50000
    }
    if (apartment.parking) {
        price += 50000
    }
    return price.toString().chunked(3).joinToString(".") // Định dạng số với dấu chấm hàng nghìn
}

// Hàm để tính toán tổng số tiền hàng tháng
fun calculateMonthlyRent(apartment: Apartment): Double {
    var totalAmount = apartment.rent +
            apartment.internet_fee +
            apartment.garbage_fee +
            (apartment.water_usage_current ?: 0)

    // Kiểm tra và cộng thêm 100,000 đồng nếu một trong các trạng thái là true
    if (apartment.gym || apartment.swimming_pool || apartment.laundry || apartment.parking) {
        totalAmount += 100000
    }

    return totalAmount.toDouble()
}
