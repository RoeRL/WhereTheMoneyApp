package com.example.wheremymoneyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passLogin: EditText
    private lateinit var loginButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_design)

        mAuth = FirebaseAuth.getInstance()

        emailLogin = findViewById(R.id.emailForm)
        passLogin = findViewById(R.id.passwordForm)
        loginButton = findViewById(R.id.loginButton)

        //TODO: Get Database Login Information[email, password]
        //TODO: Check If Login Input is equal with the login information from database

        loginButton.setOnClickListener {
            userLoginFunction()
        }
    }


    private fun userLoginFunction(){
        val email = emailLogin.text.toString().trim()
        val password = passLogin.text.toString().trim()


        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this@LoginActivity, "Kolom Login atau Password Kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login berhasil
                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                    setContentView(R.layout.activity_main)
                } else {
                    // Login gagal
                    Toast.makeText(
                        this@LoginActivity,
                        "Login gagal. Periksa email dan password Anda.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}