package com.luaga.apartmentmanagement
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.luaga.apartmentmanagement.databinding.ActivityUpdateApartmentBinding
import com.squareup.picasso.Picasso
import java.util.UUID

class UpdateApartmentActivity : AppCompatActivity() {
    lateinit var updateApartmentBinding: ActivityUpdateApartmentBinding

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUri : Uri? = null
    val firebaseStorage : FirebaseStorage = FirebaseStorage.getInstance()
    val storageReference : StorageReference = firebaseStorage.reference

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val currentUserId = currentUser?.uid
    private val reference : DatabaseReference = database.reference.child("users").child("$currentUserId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateApartmentBinding = ActivityUpdateApartmentBinding.inflate(layoutInflater)
        val view = updateApartmentBinding.root
        setContentView(view)

        //  Regiter
        registerActivityForResult()

        getAndSetData()
        updateApartmentBinding.buttonUpdateItem.setOnClickListener{
            uploadPhoto()
        }
        updateApartmentBinding.buttonCancel.setOnClickListener {
            finish()
        }
        updateApartmentBinding.updateApartmentProfileImage.setOnClickListener{
            chooseImage()
        }


    }
    fun chooseImage(){

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            //activityResultLauncher
            activityResultLauncher.launch(intent)
    }

    fun registerActivityForResult(){
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                    result ->
                val resultCode = result.resultCode
                val imageData = result.data
                if(resultCode == RESULT_OK && imageData != null){
                    imageUri = imageData.data
                    //Picasso
                    imageUri?.let {
                        Picasso.get().load(it).into(updateApartmentBinding.updateApartmentProfileImage)
                    }
                }
            })
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
        val imageUrl = intent.getStringExtra("imageUrl").toString()

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
        Picasso.get().load(imageUrl).into(updateApartmentBinding.updateApartmentProfileImage)
    }
    fun updateData(imageURL: String,imageName: String){
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
        apartmentMap["url"]= imageURL
        apartmentMap["imageName"]= imageName

        reference.child("apartments").child(apartmentId).updateChildren(apartmentMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Cập nhật căn hộ thành công", Toast.LENGTH_SHORT).show()
                    updateApartmentBinding.buttonUpdateItem.isClickable= true
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Lỗi khi cập nhật căn hộ: ${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun uploadPhoto(){
        updateApartmentBinding.buttonUpdateItem.isClickable = false

        //UUID
        val imageName = intent.getStringExtra("imageName").toString()
        val imageReference = storageReference.child("images").child(imageName)

        imageUri?.let{
                uri -> imageReference.putFile(uri).addOnSuccessListener {
            Toast.makeText(applicationContext,"Image updated", Toast.LENGTH_SHORT).show()
            // downloadable url
            val myUploadImageReference = storageReference.child("images").child(imageName)

            myUploadImageReference.downloadUrl.addOnSuccessListener { url ->
                val imageURL = url.toString()
                updateData(imageURL,imageName)
            }
        }.addOnFailureListener {
            Toast.makeText(applicationContext,it.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        }
    }

}