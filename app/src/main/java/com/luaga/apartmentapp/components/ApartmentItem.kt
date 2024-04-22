import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import  androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luaga.apartmentapp.data.Apartment

@Composable
fun ApartmentItem(apartment: Apartment, onClick: () -> Unit) {
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
                text = "Apartment Information",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Apartment Number: ${apartment.apartment_number}")
            Text(text = "Floor: ${apartment.floor}")
            Text(text = "Number of Bedrooms: ${apartment.num_bedrooms}")
            Text(text = "Number of Bathrooms: ${apartment.num_bathrooms}")
            Text(text = "Area: ${apartment.area_sqft} sqft")
            Text(text = "Rent: ${apartment.rent}/month")
            Text(text = "Status: ${apartment.status}")
        }
    }

}
