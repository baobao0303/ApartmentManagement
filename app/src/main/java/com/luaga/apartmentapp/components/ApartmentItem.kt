import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import  androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luaga.apartmentapp.data.Apartment

@Composable
fun ApartmentItem(
    apartment: Apartment,
    onClick: () -> Unit,
    onUserButtonClick: () -> Unit
) {
   Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Thông tin căn hộ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Số căn hộ: ${apartment.apartment_number}")
            Text(text = "Tầng: ${apartment.floor}")
            Text(text = "Số phòng ngủ: ${apartment.num_bedrooms}")
            Text(text = "Số phòng tắm: ${apartment.num_bathrooms}")
            Text(text = "Diện tích: ${apartment.area_sqft} sqft")
            Text(text = "Giá thuê: ${apartment.rent}/tháng")
            Text(text = "Tình trạng: ${apartment.status}")
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onUserButtonClick() },
                modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
            ) {
                Text("Thông tin người thuê căn hộ")
            }
        }
    }

}
