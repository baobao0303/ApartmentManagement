package com.luaga.apartmentmanagement

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
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
import com.luaga.apartmentmanagement.data.Apartments
import com.luaga.apartmentmanagement.databinding.ActivityAddApartmentBinding
import com.squareup.picasso.Picasso
import java.util.UUID

class AddApartmentActivity : AppCompatActivity() {
    private lateinit var addApartmentBinding: ActivityAddApartmentBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUri : Uri? = null
    val firebaseStorage : FirebaseStorage = FirebaseStorage.getInstance()
    val storageReference : StorageReference = firebaseStorage.reference
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val currentUserId = currentUser?.uid
    private val reference : DatabaseReference = database.reference.child("users").child("$currentUserId").child("apartments")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addApartmentBinding = ActivityAddApartmentBinding.inflate(layoutInflater)
        setContentView(addApartmentBinding.root)
        //  Regiter
        registerActivityForResult()
        addApartmentBinding.buttonAddItem.setOnClickListener {
            uploadPhoto()
        }

        addApartmentBinding.buttonCancel.setOnClickListener {
            finish()
        }
        addApartmentBinding.apartmentProfileImage.setOnClickListener{
            chooseImage()
        }

    }
    fun chooseImage(){
        val permision = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            Manifest.permission.READ_MEDIA_IMAGES
        }else{
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        if(ContextCompat.checkSelfPermission(this,permision) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(permision),1)
        }else{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            //activityResultLauncher
            activityResultLauncher.launch(intent)
        }
    }
    fun registerActivityForResult(){
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                result ->
                    val resultCode = result.resultCode
                    val imageData = result.data
                    if(resultCode == RESULT_OK && imageData != null){
                        imageUri = imageData.data
                        //Picasso
                        imageUri?.let {
                            Picasso.get().load(it).into(addApartmentBinding.apartmentProfileImage)
                        }
                    }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            //activityResultLauncher
            activityResultLauncher.launch(intent)
        }
    }


    private fun addApartmentToDatabase(url: String, imageName: String) {
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
            numBedrooms, numBathrooms, gymService, laundryService, parkingService, swimmingService,
            url, imageName
        )
        reference.child(id).setValue(apartment).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "Đã thêm căn hộ mới", Toast.LENGTH_SHORT).show()
                addApartmentBinding.buttonAddItem.isClickable = true
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun uploadPhoto(){
        addApartmentBinding.buttonAddItem.isClickable = false

        //UUID
        val imageName = UUID.randomUUID().toString()
        val imageReference = storageReference.child("images").child(imageName)

        imageUri?.let{
            uri -> imageReference.putFile(uri).addOnSuccessListener {
                Toast.makeText(applicationContext,"Image upload", Toast.LENGTH_SHORT).show()
                // downloadable url
                val myUploadImageReference = storageReference.child("images").child(imageName)

                myUploadImageReference.downloadUrl.addOnSuccessListener { url ->
                    val imageURL = url.toString()
                    addApartmentToDatabase(imageURL,imageName)
                }
            }.addOnFailureListener {
            Toast.makeText(applicationContext,it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
