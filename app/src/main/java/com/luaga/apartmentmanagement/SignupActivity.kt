package com.luaga.apartmentmanagement

import android.os.Bundle
import android.util.Size
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.luaga.apartmentmanagement.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding
    var buttonBack: Button? = null

    // Authentication with Firebase
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    // Database Realtime
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    // Database admin get infor user
    var reference: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)

        buttonBack = signupBinding.buttonBack

        signupBinding.buttonSignup.setOnClickListener {
            val username = signupBinding.editTextSignUpUsername.text.toString()
            val phone = signupBinding.editTextSignUpPhone.text.toString()
            val email = signupBinding.editTextSignUpEmail.text.toString()
            val password = signupBinding.editTextSignUpPassword.text.toString()
            val confirmPassword = signupBinding.editTextSignUpConfirmPassword.text.toString()
            val checked = signupBinding.checkBox

            if (!isValidEmail(email)) {
                showInvalidEmailDialog()
            } else if(!isValidPassword(password)) {
                showInvalidPasswordDialog()
            } else if(password != confirmPassword){
                showPasswordMismatchDialog()
            } else {
                if (checked.isChecked) {
                    // Email format and passwords match, check for existing account
                    checkExistingAccount(email, password, username, phone)
                } else {
                    // Inform the user to check the checkbox
                    showCheckboxNotCheckedDialog()
                }
            }
        }

        buttonBack!!.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showCheckboxNotCheckedDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chưa Chọn Checkbox")
        builder.setMessage("Vui lòng chọn checkbox để tiếp tục.")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
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

    private fun checkExistingAccount(email: String, password: String, username: String, phone: String) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInMethods
                if (signInMethods != null && signInMethods.isNotEmpty()) {
                    // Email already exists, inform the user
                    showExistingAccountDialog()
                } else {
                    // Email does not exist, continue with sign-up process
                    signupWithFirebase(email, password)
                    val helperClass = HelperClass()
                    helperClass.setUsername(username)
                    helperClass.setPhone(phone)
                    helperClass.setEmail(email)
                    helperClass.setPassword(password)
                    reference = database.getReference("user")
                    reference.child(username).setValue(helperClass)
                    // List Apartment
                    reference = database.getReference("user").child(username).child("apartments")
                    // Push the default apartments to Firebase under the user's node
                    val defaultApartment1 = InforApartment("John Doe", "1234567890", "123 Main St", "john@example.com", 1000.0, 50.0, 75.0, 20.0, true, false, true)
                    val defaultApartment2 = InforApartment("Jane Smith", "0987654321", "456 Elm St", "jane@example.com", 1200.0, 60.0, 80.0, 25.0, false, true, false)
                    reference.push().setValue(defaultApartment1)
                    reference.push().setValue(defaultApartment2)
                }
            } else {
                // Error occurred while checking for existing account
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showExistingAccountDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tài Khoản Đã Tồn Tại")
        builder.setMessage("Một tài khoản đã tồn tại với địa chỉ email này. Vui lòng sử dụng một địa chỉ email khác.")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun signupWithFirebase(email: String, password: String) {
        signupBinding.progressBarSignup.visibility = View.VISIBLE
        signupBinding.buttonSignup.isClickable = false
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Your account has been created", Toast.LENGTH_LONG).show()
                finish()
                signupBinding.progressBarSignup.visibility = View.INVISIBLE
                signupBinding.buttonSignup.isClickable= true
            } else {
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
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
