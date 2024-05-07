package com.luaga.apartmentmanagement

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.luaga.apartmentmanagement.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    var auth = FirebaseAuth.getInstance()
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)
        registerAcitivityForGoogleSignIn()
        // Sau khi loginBinding được khởi tạo, bạn có thể truy cập đến các thành phần của nó
        val textOfGoogleButton = loginBinding.buttonGoogleSignin.getChildAt(0) as TextView
        textOfGoogleButton.text = "Continue with Google"
        textOfGoogleButton.setTextColor(Color.GRAY)
        textOfGoogleButton.textSize = 14F

        loginBinding.buttonSignin.setOnClickListener{
            val userEmail = loginBinding.editTextLoginEmail.text.toString()
            val userPassword = loginBinding.editTextLoginPassword.text.toString()
            signInUser(userEmail,userPassword)
        }

        loginBinding.textViewSignup.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        loginBinding.textViewForgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonGoogleSignin.setOnClickListener {
            signInGoogle()
        }
    }

    fun signInUser (userEmail: String, userPassword: String){
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{
                task -> if(task.isSuccessful){
            Toast.makeText(applicationContext,"Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(applicationContext,task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser

        if(user !=  null){
            Toast.makeText(applicationContext,"Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun signInGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("640621454526-rb0tsfr7105v538ab8augas56j3qu78a.apps.googleusercontent.com")
            .build() // Add build() here to get GoogleSignInOptions instance
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        signIn()
    }
    private fun signIn(){
        val signInIntent : Intent = googleSignInClient.signInIntent
        activityResultLauncher.launch(signInIntent)
    }
    private fun registerAcitivityForGoogleSignIn(){
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                val resultCode = result.resultCode
                val data = result.data
                if(resultCode == RESULT_OK && data != null){
                    val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                    firebaseSignInWithGoogle(task)
                }
            })
    }
    private  fun firebaseSignInWithGoogle(task: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            Toast.makeText(applicationContext,"Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            firebaseSignInWithGoogle(account)
        }catch (e:ApiException){
            Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
    private fun firebaseSignInWithGoogle(account: GoogleSignInAccount){
        val authCredential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(authCredential).addOnCompleteListener { task ->
            if(task.isSuccessful){

            }
            else{

            }
        }
    }

}
