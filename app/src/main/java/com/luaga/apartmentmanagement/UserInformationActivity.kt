package com.luaga.apartmentmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserInformationActivity : AppCompatActivity() {
    // Khai báo các biến cho thông tin căn hộ
    private var apartmentNumber: String = ""
    private var area: Double = 0.0
    private var floor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        // Lấy dữ liệu từ intent
        val apartmentNumber = intent.getStringExtra("apartmentNumber")
        val area = intent.getDoubleExtra("area", 0.0)
        val floor = intent.getIntExtra("floor", 0)

        // Hiển thị dữ liệu trên các TextView
        findViewById<TextView>(R.id.apartmentNumberTextView).text = apartmentNumber
        findViewById<TextView>(R.id.areaTextView).text = area.toString()
        findViewById<TextView>(R.id.floorTextView).text = floor.toString()

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