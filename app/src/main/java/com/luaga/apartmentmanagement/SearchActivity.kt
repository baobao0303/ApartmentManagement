package com.luaga.apartmentmanagement

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_search
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

                R.id.bottom_search -> return@setOnItemSelectedListener true
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
        supportActionBar?.title = "Search"
    }
}