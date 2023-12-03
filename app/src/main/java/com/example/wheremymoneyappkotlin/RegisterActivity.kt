package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val closeButton: ImageButton
        closeButton = findViewById(R.id.closeButton)

        database = FirebaseDatabase.getInstance()

        val m_registerButton = findViewById<Button>(R.id.registerButton)
        val m_emailLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.registEmailLayout)
        val m_usernameLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.registUsernameLayout)
        val m_passwordLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.registPasswordLayout)
        val m_confirmPasswordLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.registConfirmPasswordLayout)


        closeButton.setOnClickListener {
            finish()
        }

        m_registerButton.setOnClickListener {
            val emailForm = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.registEmail).text.toString()
            val usernameForm = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.registUsername).text.toString()
            val passForm = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.registPassword).text.toString()
            val confPassForm = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.registConfirmPassword).text.toString()

            if (emailForm.isEmpty()) {
                m_emailLayout.error = "Mohon Masukkan Email!"
            }
            if (usernameForm.isEmpty()) {
                m_usernameLayout.error = "Mohon Masukkan Username"
            }
            if (passForm.isEmpty()) {
                m_passwordLayout.error = "Mohon Masukkan Password"
            }
            if (confPassForm.isEmpty()) {
                m_confirmPasswordLayout.error = "Mohon Confirmasi Password"
            }
            if (passForm != confPassForm){
                m_passwordLayout.error = "Password Tidak Sama dengan confirmasi Password"
                m_confirmPasswordLayout.error = "Password Tidak Sama dengan confirmasi Password"
                return@setOnClickListener
            }

            if (emailForm.isNotEmpty() && usernameForm.isNotEmpty() && passForm.isNotEmpty() && confPassForm.isNotEmpty() && passForm == confPassForm){
                submitToFirebase(emailForm, usernameForm, passForm)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun submitToFirebase(email: String, username: String, password: String){
        val databaseReference = database.reference.child("user_list")
        val transactionId = databaseReference.push().key
        val transactionData = hashMapOf("email" to email, "username" to username, "password" to password)

        if (transactionId != null) {
            databaseReference.child(transactionId).setValue(transactionData)
        }
    }
}