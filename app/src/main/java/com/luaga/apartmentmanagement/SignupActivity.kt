package com.luaga.apartmentmanagement

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.luaga.apartmentmanagement.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding
    var buttonBack: Button? = null
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
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

            if (!isValidEmail(email)) {
                showInvalidEmailDialog()
            } else if(!isValidPassword(password)) {
                showInvalidPasswordDialog()
            } else if(password != confirmPassword){
                showPasswordMismatchDialog()
            } else {
                // Email format and passwords match, continue with sign-up process
                signupWithFirebase(email,password)
            }
        }

        buttonBack!!.setOnClickListener {
            onBackPressed()
        }
    }
    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.count { it == '@' } == 1
    }
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 7
    }
    private fun showInvalidEmailDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Email Không Hợp Lệ")
        builder.setMessage("Vui lòng nhập địa chỉ email hợp lệ chỉ chứa một ký tự '@'.")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun signupWithFirebase(
        email: String,
        password: String
    ){
        signupBinding.progressBarSignup.visibility = View.VISIBLE
        signupBinding.buttonSignup.isClickable = false
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            task -> if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Your account has been created", Toast.LENGTH_LONG).show()
                        finish()
                        signupBinding.progressBarSignup.visibility = View.INVISIBLE
                        signupBinding.buttonSignup.isClickable= true
                    }else {
                        Toast.makeText(applicationContext,task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
        }
    }

    private fun showInvalidPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mật Khẩu Không Hợp Lệ")
        builder.setMessage("Vui lòng nhập mật khẩu có ít nhất 7 ký tự.")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showPasswordMismatchDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mật Khẩu Không Trùng Khớp")
        builder.setMessage("Mật khẩu nhập lại không khớp với mật khẩu đã nhập. Vui lòng kiểm tra lại.")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}