package com.luaga.apartmentmanagement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luaga.apartmentmanagement.databinding.ActivityUpdateApartmentBinding

class UpdateApartmentActivity : AppCompatActivity() {
    lateinit var updateApartmentBinding: ActivityUpdateApartmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateApartmentBinding = ActivityUpdateApartmentBinding.inflate(layoutInflater)
        val view = updateApartmentBinding.root
        setContentView(view)

    }
}