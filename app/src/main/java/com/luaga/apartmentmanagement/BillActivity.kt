package com.luaga.apartmentmanagement

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

        // Nhận thông tin căn hộ từ Intent
        val apartmentNumber = intent.getStringExtra("apartmentNumber")
        val area = intent.getIntExtra("area", 0)
        val floor = intent.getIntExtra("floor", 0)
    }
}