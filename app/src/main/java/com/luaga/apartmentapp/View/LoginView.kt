import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.luaga.apartmentapp.ui.theme.Primary
import com.luaga.apartmentapp.ui.theme.TextColors
import com.luaga.apartmentapp.viewmodel.ApartmentViewModel

@Composable
fun LoginView(
    navController: NavController,
    viewModel: ApartmentViewModel
) {
    var usernameState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf("") }
    var showErrorMessage by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize().background(Primary)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                color = TextColors, // Use the provided text color here
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            OutlinedTextField(
                value = usernameState,
                onValueChange = { usernameState = it },
                label = {
                    Text(
                        "Username",
                        color = TextColors, // Adjust the text color here
                        style = TextStyle(
                            fontSize = 18.sp, // Adjust the text size here
                            fontWeight = FontWeight.Normal
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = TextColors
                ),
                modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
            )
            OutlinedTextField(
                value = passwordState,
                onValueChange = { passwordState = it },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = TextColors
                ),
                label = {
                    Text(
                        "Password",
                        color = TextColors, // Adjust the text color here
                        style = TextStyle(
                            fontSize = 18.sp, // Adjust the text size here
                            fontWeight = FontWeight.Normal
                        )
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Add else Icons.Filled.Clear,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
            )

            if (showErrorMessage) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            Button(
                onClick = {
                    val username = usernameState.text
                    val password = passwordState.text
                    // Xử lý logic đăng nhập ở đây
                    if (username == "admin" && password == "123") {
                        navController.navigate("home_screen")
                    } else {
                        // Xử lý trường hợp đăng nhập không thành công
                        errorMessage = if (username != "admin" && password != "123") {
                            "Tên người dùng và mật khẩu không đúng"
                        } else if (username != "admin") {
                            "Tên người dùng không đúng"
                        } else {
                            "Mật khẩu không đúng"
                        }
                        showErrorMessage = true
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Login",
                    color = TextColors, // Use the provided text color here
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
