package com.luaga.apartmentmanagement

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
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

        signupBinding.buttonSignup.setOnClickListener {
            val email = signupBinding.editTextSignUpEmail.text.toString()
            val password = signupBinding.editTextSignUpPassword.text.toString()
            val confirmPassword = signupBinding.editTextSignUpConfirmPassword.text.toString()

            if (!email.contains("@")) {
                showInvalidEmailDialog()
            } else if(password != confirmPassword){
                showPasswordMismatchDialog()
            } else {
                // Email format and passwords match, continue with sign-up process
            }
        }

        buttonBack!!.setOnClickListener {
            onBackPressed()
        }
    }
    private fun showInvalidEmailDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Invalid Email")
        builder.setMessage("Please enter a valid email address.")
        builder.setPositiveButton("OK") { dialog, which ->
            // Do nothing, just dismiss the dialog
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showPasswordMismatchDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Password Mismatch")
        builder.setMessage("The passwords entered do not match. Please make sure your passwords match.")
        builder.setPositiveButton("OK") { dialog, which ->
            // Do nothing, just dismiss the dialog
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}