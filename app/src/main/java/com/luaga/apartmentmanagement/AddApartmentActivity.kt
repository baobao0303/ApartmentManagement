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
    lateinit var  addApartmentBinding: ActivityAddApartmentBinding

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val currentUserId = currentUser?.uid
    val reference : DatabaseReference= database.reference.child("users").child("$currentUserId").child("apartments")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addApartmentBinding = ActivityAddApartmentBinding.inflate(layoutInflater)
        val view = addApartmentBinding.root
        setContentView(view)
        addApartmentBinding.buttonAddItem.setOnClickListener{
            addApartmentToDatabase()
        }
    }
    fun addApartmentToDatabase(){
        val apartmentNumber: String = addApartmentBinding.addApartmentNumber.text.toString()
        val floor: Int = addApartmentBinding.addFloor.text.toString().toInt()
        val area: Int = addApartmentBinding.addArea.text.toString().toInt()
        val price: Int = addApartmentBinding.addPrice.text.toString().toInt()
        val priceGarbage: Int = addApartmentBinding.addPriceGarbage.text.toString().toInt()
        val priceInternet: Int = addApartmentBinding.addPriceInternet.text.toString().toInt()
        val numBedrooms: Int = addApartmentBinding.addNumBedrooms.text.toString().toInt()
        val numBathrooms: Int = addApartmentBinding.addNumBathrooms.text.toString().toInt()
        val gymService: Boolean = addApartmentBinding.addGymService.isChecked
        val laundryService: Boolean = addApartmentBinding.addLaundryService.isChecked
        val parkingService: Boolean = addApartmentBinding.addParkingService.isChecked
        val swimmingService: Boolean = addApartmentBinding.addSwimmingService.isChecked

        val id : String = reference.push().key.toString()

        val apartment = Apartments(
            id, apartmentNumber, floor, area, price, priceGarbage, priceInternet, numBedrooms, numBathrooms,
            gymService, laundryService, parkingService, swimmingService
        )
        reference.child(id).setValue(apartment).addOnCompleteListener{
                task -> if (task.isSuccessful){
            Toast.makeText(applicationContext,"Đã thêm căn hộ mới", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(applicationContext,task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
        }
    }
}