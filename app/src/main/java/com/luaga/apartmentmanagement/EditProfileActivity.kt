package com.luaga.apartmentmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luaga.apartmentmanagement.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(){
    lateinit var editProfileBinding: ActivityEditProfileBinding
    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editProfileBinding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = editProfileBinding.root
        setContentView(view)

        getCurrentUserData()

        editProfileBinding.buttonSave.setOnClickListener{
            saveProfileData()
            finish()
        }
    }
    private fun getCurrentUserData() {
        val currentUser = auth.currentUser
        val uid = currentUser?.uid

        uid?.let { uid ->
            val userRef = database.reference.child("users").child(uid)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val username = snapshot.child("username").getValue(String::class.java)
                    val phone = snapshot.child("phone").getValue(String::class.java)
                    val address = snapshot.child("address").getValue(String::class.java)
                    val facebook = snapshot.child("facebook").getValue(String::class.java)

                    // Đặt dữ liệu vào các trường nhập liệu
                    editProfileBinding.username.setText(username)
                    editProfileBinding.phone.setText(phone)
                    editProfileBinding.address.setText(address)
                    editProfileBinding.facebook.setText(facebook)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Xử lý khi có lỗi
                }
            })
        }
    }
    private fun saveProfileData() {
        val currentUser = auth.currentUser
        val uid = currentUser?.uid

        val username = editProfileBinding.username.text.toString()
        val phone = editProfileBinding.phone.text.toString()
        val address = editProfileBinding.address.text.toString()
        val facebook = editProfileBinding.facebook.text.toString()

        uid?.let { uid ->
            val userRef = database.reference.child("users").child(uid)
            userRef.child("username").setValue(username)
            userRef.child("phone").setValue(phone)
            userRef.child("address").setValue(address)
            userRef.child("facebook").setValue(facebook)
        }
    }
}