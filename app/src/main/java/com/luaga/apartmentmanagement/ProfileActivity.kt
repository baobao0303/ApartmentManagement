package com.luaga.apartmentmanagement

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luaga.apartmentmanagement.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {
    lateinit var profileBinding: ActivityProfileBinding
    lateinit var reference: DatabaseReference
    // Tạo một đối tượng FirebaseAuth
    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        val view = profileBinding.root
        setContentView(view)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_profile
        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            MainActivity::class.java
                        )
                    )

                    finish()
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_search -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            SearchActivity::class.java
                        )
                    )

                    finish()
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_settings -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            SettingActivity::class.java
                        )
                    )

                    finish()
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_profile -> return@setOnItemSelectedListener true
            }
            false
        }
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Người dùng đã đăng nhập, lấy User UID
            val uid: String = currentUser.uid
            println("User UID1: $uid")
            reference = FirebaseDatabase.getInstance().reference.child("users")
                .child(uid)
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userName: String = snapshot.child("username").value as String
                    val email: String = snapshot.child("email").value as String
                    val phone: String = snapshot.child("phone").value as String
                    val address: String = if (snapshot.hasChild("address")) {
                        snapshot.child("address").value as String
                    } else {
                        "trống"
                    }
                    val facebook: String = if (snapshot.hasChild("facebook")) {
                        snapshot.child("facebook").value as String
                    } else {
                        "trống"
                    }
                    profileBinding.textUsername.text = userName
                    profileBinding.textEmail.text = email
                    profileBinding.textPhone.text = phone
                    profileBinding.textFacebook.text = facebook
                    profileBinding.textAddress.text = address
                }

                override fun onCancelled(error: DatabaseError) {
                    // Xử lý khi có lỗi
                }
            })


        } else {
            // Người dùng chưa đăng nhập
            println("User chưa đăng nhập.")
        }
        profileBinding.buttonEdit.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

    }
}