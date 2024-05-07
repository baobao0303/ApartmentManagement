package com.luaga.apartmentmanagement

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luaga.apartmentmanagement.data.Renter
import com.luaga.apartmentmanagement.databinding.ActivityEditUserBinding
import com.luaga.apartmentmanagement.databinding.ActivityMainBinding

class EditUser : AppCompatActivity() {
    private lateinit var editUserBinding: ActivityEditUserBinding
    private lateinit var reference: DatabaseReference
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editUserBinding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(editUserBinding.root)

        editUserBinding.buttonCancel.setOnClickListener {
            finish()
        }

        // Get the apartmentId from the intent
        val apartmentId = intent.getStringExtra("apartmentId")

        // Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null && apartmentId != null) {
            val uid: String = currentUser.uid
            reference = FirebaseDatabase.getInstance().reference
                .child("users").child(uid).child("apartments").child(apartmentId).child("renter")

            // Retrieve existing renter information from Firebase
            reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val renter = dataSnapshot.getValue(Renter::class.java)
                        renter?.let {
                            // Populate the input fields with existing data
                            editUserBinding.username.setText(it.username)
                            editUserBinding.cardNumber.setText(it.cardNumber)
                            editUserBinding.phone.setText(it.phone)
                            editUserBinding.address.setText(it.address)
                            editUserBinding.date.setText(it.date)
                            editUserBinding.exData.setText(it.exDate)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "Failed to retrieve renter information: ${databaseError.message}")
                }
            })

            editUserBinding.buttonUpdateItem.setOnClickListener {
                // Retrieve renter information from the input fields
                val username = editUserBinding.username.text.toString().trim()
                val cardNumber = editUserBinding.cardNumber.text.toString().trim()
                val phone = editUserBinding.phone.text.toString().trim()
                val address = editUserBinding.address.text.toString().trim()
                val date = editUserBinding.date.text.toString().trim()
                val exDate = editUserBinding.exData.text.toString().trim()

                // Create a map to hold the renter's information
                val renterInfo = mapOf(
                    "username" to username,
                    "cardNumber" to cardNumber,
                    "phone" to phone,
                    "address" to address,
                    "date" to date,
                    "exDate" to exDate
                )

                // Push renter information to Firebase
                reference.setValue(renterInfo)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Rental information saved successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to save rental information", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

}