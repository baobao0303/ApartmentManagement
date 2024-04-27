package com.luaga.apartmentmanagement

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.luaga.apartmentmanagement.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding
    var buttonBack: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)

        buttonBack = signupBinding.buttonBack

        signupBinding.buttonSignup.setOnClickListener {}

        buttonBack!!.setOnClickListener {
            // Xử lý sự kiện khi button được nhấn
            // Ví dụ: Quay về trang trước đó
            onBackPressed()
        }
    }
}