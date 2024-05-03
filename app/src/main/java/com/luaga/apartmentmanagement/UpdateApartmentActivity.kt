package com.luaga.apartmentmanagement
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.luaga.apartmentmanagement.databinding.ActivityUpdateApartmentBinding

class UpdateApartmentActivity : AppCompatActivity() {
    lateinit var updateApartmentBinding: ActivityUpdateApartmentBinding
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val currentUserId = currentUser?.uid
    private val reference : DatabaseReference = database.reference.child("users").child("$currentUserId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateApartmentBinding = ActivityUpdateApartmentBinding.inflate(layoutInflater)
        val view = updateApartmentBinding.root
        setContentView(view)
        getAndSetData()
        updateApartmentBinding.buttonUpdateItem.setOnClickListener{
            updateData()
        }
        updateApartmentBinding.buttonCancel.setOnClickListener {
            finish()
        }
    }
    fun getAndSetData(){
        val apartmentNumber = intent.getStringExtra("apartmentNumber")
        val area = intent.getStringExtra("area")
        val floor = intent.getStringExtra("floor")
        val numberBedrooms = intent.getStringExtra("numberBedrooms")
        val numberBathrooms = intent.getStringExtra("numberBathrooms")
        val price = intent.getStringExtra("price")
        val priceInternet = intent.getStringExtra("priceInternet")
        val priceGarbage = intent.getStringExtra("priceGarbage")
        val gymService = intent.getBooleanExtra("gymService", false)
        val laundryService = intent.getBooleanExtra("laundryService", false)
        val parkingService = intent.getBooleanExtra("parkingService", false)
        val swimmingService = intent.getBooleanExtra("swimmingService", false)

        updateApartmentBinding.updateApartmentNumber.setText(apartmentNumber)
        updateApartmentBinding.updateArea.setText(area)
        updateApartmentBinding.updateFloor.setText(floor)
        updateApartmentBinding.updateNumBedrooms.setText(numberBedrooms)
        updateApartmentBinding.updateNumBathrooms.setText(numberBathrooms)
        updateApartmentBinding.updatePrice.setText(price)
        updateApartmentBinding.updatePriceInternet.setText(priceInternet)
        updateApartmentBinding.updatePriceGarbage.setText(priceGarbage)
        updateApartmentBinding.updateGymService.isChecked = gymService
        updateApartmentBinding.updateLaundryService.isChecked = laundryService
        updateApartmentBinding.updateParkingService.isChecked = parkingService
        updateApartmentBinding.updateSwimmingService.isChecked = swimmingService
    }
    fun updateData() {
        val updatedApartmentNumber = updateApartmentBinding.updateApartmentNumber.text.toString()
        val updatedArea = updateApartmentBinding.updateArea.text.toString().toDoubleOrNull() ?: 0.0
        val updatedFloor = updateApartmentBinding.updateFloor.text.toString().toIntOrNull() ?: 0
        val updatedNumberBedrooms = updateApartmentBinding.updateNumBedrooms.text.toString().toIntOrNull() ?: 0
        val updatedNumberBathrooms = updateApartmentBinding.updateNumBathrooms.text.toString().toIntOrNull() ?: 0
        val updatedPrice = updateApartmentBinding.updatePrice.text.toString().toDoubleOrNull() ?: 0.0
        val updatedPriceInternet = updateApartmentBinding.updatePriceInternet.text.toString().toDoubleOrNull() ?: 0.0
        val updatedPriceGarbage = updateApartmentBinding.updatePriceGarbage.text.toString().toDoubleOrNull() ?: 0.0

        val updatedGymService = updateApartmentBinding.updateGymService.isChecked
        val updatedLaundryService = updateApartmentBinding.updateLaundryService.isChecked
        val updatedParkingService = updateApartmentBinding.updateParkingService.isChecked
        val updatedSwimmingService = updateApartmentBinding.updateSwimmingService.isChecked

        val apartmentId = intent.getStringExtra("id") ?: ""

        val apartmentMap = HashMap<String, Any>()
        apartmentMap["apartmentNumber"] = updatedApartmentNumber
        apartmentMap["area"] = updatedArea
        apartmentMap["floor"] = updatedFloor
        apartmentMap["numberBedrooms"] = updatedNumberBedrooms
        apartmentMap["numberBathrooms"] = updatedNumberBathrooms
        apartmentMap["price"] = updatedPrice
        apartmentMap["priceInternet"] = updatedPriceInternet
        apartmentMap["priceGarbage"] = updatedPriceGarbage
        apartmentMap["gymService"] = updatedGymService
        apartmentMap["laundryService"] = updatedLaundryService
        apartmentMap["parkingService"] = updatedParkingService
        apartmentMap["swimmingService"] = updatedSwimmingService

        reference.child("apartments").child(apartmentId).updateChildren(apartmentMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Cập nhật căn hộ thành công", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Lỗi khi cập nhật căn hộ: ${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}