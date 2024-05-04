package com.luaga.apartmentmanagement

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luaga.apartmentmanagement.databinding.ActivityUserInformationBinding
import com.squareup.picasso.Picasso

class UserInformationActivity : AppCompatActivity() {
    // Khai báo các biến cho thông tin căn hộ
    private var apartmentNumber: String = ""
    private var area: Double = 0.0
    private var floor: Int = 0
    private var price: Double = 0.0
    private var priceGarbage: Double = 0.0
    private var priceInternet: Double = 0.0
    private var numBedrooms: Int = 0
    private var numBathrooms: Int = 0
    private var gymService: Boolean = false
    private var laundryService: Boolean = false
    private var parkingService: Boolean = false
    private var swimmingService: Boolean = false
    private var url: String = ""
    private var imageName: String = ""

    lateinit var  userInformationBinding: ActivityUserInformationBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInformationBinding = ActivityUserInformationBinding.inflate(layoutInflater)
        val view = userInformationBinding.root
        setContentView(view)

        // Lấy dữ liệu từ intent
        apartmentNumber = intent.getStringExtra("apartmentNumber").toString()
        area = intent.getDoubleExtra("area", 0.0)
        floor = intent.getIntExtra("floor", 0)
        price = intent.getDoubleExtra("price", 0.0)
        priceGarbage = intent.getDoubleExtra("priceGarbage", 0.0)
        priceInternet = intent.getDoubleExtra("priceInternet", 0.0)
        numBedrooms = intent.getIntExtra("numberBedrooms", 0)
        numBathrooms = intent.getIntExtra("numberBathrooms", 0)
        gymService = intent.getBooleanExtra("gymService", false)
        laundryService = intent.getBooleanExtra("laundryService", false)
        parkingService = intent.getBooleanExtra("parkingService", false)
        swimmingService = intent.getBooleanExtra("swimmingService", false)
        url = intent.getStringExtra("imageUrl").toString()
        imageName = intent.getStringExtra("imageName").toString()

        // Hiển thị dữ liệu trên các TextView

        userInformationBinding.apartmentNumber.text = apartmentNumber
        userInformationBinding.area.text = "${convertToFormattedString(area.toInt())} (m^2)"
        userInformationBinding.floor.text = floor.toString()
        userInformationBinding.price.text = "${convertToFormattedString(price.toInt())} VND"
        userInformationBinding.priceGarbage.text = "${convertToFormattedString(priceGarbage.toInt())} VND"
        userInformationBinding.priceInternet.text = "${convertToFormattedString(priceInternet.toInt())} VND"
        userInformationBinding.numBedrooms.text = numBedrooms.toString()
        userInformationBinding.numBathrooms.text = numBathrooms.toString()
        userInformationBinding.gymService.isChecked = gymService
        userInformationBinding.laundryService.isChecked= laundryService
        userInformationBinding.parkingService.isChecked = parkingService
        userInformationBinding.swimmingService.isChecked = swimmingService
        Picasso.get().load(url).into(userInformationBinding.imageView5)
        userInformationBinding.imageView5.layoutParams.width = 1200
        userInformationBinding.imageView5.layoutParams.height = 1000
        userInformationBinding.buttonBack.setOnClickListener{
            finish()
        }
    }
    fun convertToFormattedString(number: Int): String {
        val formattedNumber = StringBuilder(number.toString()).reverse()
        var count = 0
        var result = ""
        for (char in formattedNumber) {
            if (count == 3) {
                result += "."
                count = 0
            }
            result += char
            count++
        }
        return result.reversed()
    }


    private fun showBill() {
        // Tạo Intent để mở BillActivity
        val intent = Intent(this, BillActivity::class.java)

        // Truyền thông tin căn hộ qua Intent
        intent.putExtra("apartmentNumber", apartmentNumber)
        intent.putExtra("area", area)
        intent.putExtra("floor", floor)
        // Thêm các thông tin căn hộ khác tương tự ở đây

        // Khởi động BillActivity với Intent đã cấu hình
        startActivity(intent)
    }
}