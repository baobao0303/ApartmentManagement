package com.luaga.apartmentmanagement

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.luaga.apartmentmanagement.databinding.ActivitySettingBinding


class SettingActivity : AppCompatActivity() {
    lateinit var  settingBinding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingBinding = ActivitySettingBinding.inflate(layoutInflater)
        val view = settingBinding.root
        setContentView(view)
        settingBinding.buttonSignout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_settings
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

                R.id.bottom_settings -> return@setOnItemSelectedListener true
                R.id.bottom_profile -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            ProfileActivity::class.java
                        )
                    )
                                        finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        supportActionBar?.title = "Setting"
    }
}