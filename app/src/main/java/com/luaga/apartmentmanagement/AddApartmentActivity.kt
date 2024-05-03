package com.luaga.apartmentmanagement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.luaga.apartmentmanagement.data.Apartments
import com.luaga.apartmentmanagement.databinding.ActivityAddApartmentBinding

class AddApartmentActivity : AppCompatActivity() {
    private lateinit var addApartmentBinding: ActivityAddApartmentBinding

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val currentUserId = currentUser?.uid
    private val reference : DatabaseReference = database.reference.child("users").child("$currentUserId").child("apartments")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addApartmentBinding = ActivityAddApartmentBinding.inflate(layoutInflater)
        setContentView(addApartmentBinding.root)

        addApartmentBinding.buttonAddItem.setOnClickListener {
            addApartmentToDatabase()
        }

        addApartmentBinding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun addApartmentToDatabase() {
        val apartmentNumber: String = addApartmentBinding.addApartmentNumber.text.toString()
        val floor: Int = addApartmentBinding.addFloor.text.toString().toInt()
        val area: Double = addApartmentBinding.addArea.text.toString().toDouble()
        val price: Double = addApartmentBinding.addPrice.text.toString().toDouble()
        val priceGarbage: Double = addApartmentBinding.addPriceGarbage.text.toString().toDouble()
        val priceInternet: Double = addApartmentBinding.addPriceInternet.text.toString().toDouble()
        val numBedrooms: Int = addApartmentBinding.addNumBedrooms.text.toString().toInt()
        val numBathrooms: Int = addApartmentBinding.addNumBathrooms.text.toString().toInt()
        val gymService: Boolean = addApartmentBinding.addGymService.isChecked
        val laundryService: Boolean = addApartmentBinding.addLaundryService.isChecked
        val parkingService: Boolean = addApartmentBinding.addParkingService.isChecked
        val swimmingService: Boolean = addApartmentBinding.addSwimmingService.isChecked

        if (apartmentNumber.isEmpty() || floor == 0 || area == 0.0 || price == 0.0 || priceGarbage == 0.0 ||
            priceInternet == 0.0 || numBedrooms == 0 || numBathrooms == 0) {
            // Hiển thị thông báo nếu có trường thông tin trống
            Toast.makeText(applicationContext, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        // Tiếp tục thêm căn hộ vào cơ sở dữ liệu
        val id : String = reference.push().key.toString()

        val apartment = Apartments(
            id, apartmentNumber, floor, area, price, priceGarbage, priceInternet,
            numBedrooms, numBathrooms, gymService, laundryService, parkingService, swimmingService
        )
        reference.child(id).setValue(apartment).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "Đã thêm căn hộ mới", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
