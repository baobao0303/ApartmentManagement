package com.luaga.apartmentmanagement

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.luaga.apartmentmanagement.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var forgotBinding:ActivityForgotPasswordBinding

    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgotBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = forgotBinding.root
        setContentView(view)

        // Đặt tiêu đề mới cho thanh tiêu đề
        supportActionBar?.title = "Quay lại"
        forgotBinding.buttonReset.setOnClickListener{
            val userEmail = forgotBinding.editTextForgotEmail.text.toString()
            auth.sendPasswordResetEmail(userEmail).addOnCompleteListener{
                    task -> if (task.isSuccessful){
                Toast.makeText(applicationContext, "Chúng tôi đã gửi email đặt lại mật khẩu đến địa chỉ email của bạn", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            }
        }

    }
}