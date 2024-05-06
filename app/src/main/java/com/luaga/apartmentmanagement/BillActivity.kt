package com.luaga.apartmentmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luaga.apartmentmanagement.databinding.ActivityBillBinding

class BillActivity : AppCompatActivity() {
    lateinit var billBinding: ActivityBillBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        billBinding = ActivityBillBinding.inflate(layoutInflater)
        val view = billBinding.root
        setContentView(view)

        // Nhận thông tin căn hộ từ Intent
        val apartmentNumber = intent.getStringExtra("apartmentNumber")
        val area = intent.getDoubleExtra("area", 0.0)
        val floor = intent.getIntExtra("floor", 0)
        val price = intent.getDoubleExtra("price", 0.0)
        val priceGarbage = intent.getDoubleExtra("priceGarbage", 0.0)
        val priceInternet = intent.getDoubleExtra("priceInternet", 0.0)
        val numBedrooms = intent.getIntExtra("numberBedrooms", 0)
        val numBathrooms = intent.getIntExtra("numberBathrooms", 0)
        val gymService = intent.getBooleanExtra("gymService", false)
        val laundryService = intent.getBooleanExtra("laundryService", false)
        val parkingService = intent.getBooleanExtra("parkingService", false)
        val swimmingService = intent.getBooleanExtra("swimmingService", false)

        val monthlyBill = calculateMonthlyBill(price, priceGarbage, priceInternet, gymService, laundryService, parkingService, swimmingService)

        billBinding.price.text = "${convertToFormattedString(price.toInt())} VND"
        billBinding.priceGarbage.text = "${convertToFormattedString(priceGarbage.toInt())} VND"
        billBinding.priceInternet.text = "${convertToFormattedString(priceInternet.toInt())} VND"
        billBinding.monthlyBill.text = "${convertToFormattedString(monthlyBill.toInt())} VND"

        // Hiển thị dịch vụ Gym
        if (gymService) {
            billBinding.gymService.text = "200.000 VND"
        } else {
            billBinding.gymService.text = "0 VND"
        }

        // Hiển thị dịch vụ Laundry
        if (laundryService) {
            billBinding.laundryService.text = "100.000 VND"
        } else {
            billBinding.laundryService.text = "0 VND"
        }

        // Hiển thị dịch vụ Parking
        if (parkingService) {
            billBinding.parkingService.text = "50.000 VND"
        } else {
            billBinding.parkingService.text = "0 VND"
        }

        // Hiển thị dịch vụ Swimming
        if (swimmingService) {
            billBinding.swimmingService.text = "50.000 VND"
        } else {
            billBinding.swimmingService.text = "0 VND"
        }
        billBinding.buttonCancel.setOnClickListener {
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

    private fun calculateMonthlyBill(
        price: Double,
        priceGarbage: Double,
        priceInternet: Double,
        gymService: Boolean,
        laundryService: Boolean,
        parkingService: Boolean,
        swimmingService: Boolean
    ): Double {
        var totalBill = price + priceGarbage + priceInternet * 3000
        if (gymService) totalBill += 200000
        if (laundryService) totalBill += 100000
        if (parkingService) totalBill += 50000
        if (swimmingService) totalBill += 50000

        return totalBill
    }
}
