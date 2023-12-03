package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var emailLogin: EditText
    private lateinit var passLogin: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailLogin = findViewById(R.id.emailForm)
        passLogin = findViewById(R.id.passwordForm)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        //TODO: Get Database Login Information[email, password]
        //TODO: Check If Login Input is equal with the login information from database

        loginButton.setOnClickListener {
            userLoginFunction()
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun userLoginFunction(){
        val email = emailLogin.text.toString().trim()
        val password = passLogin.text.toString().trim()


        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this@LoginActivity, "Kolom Login atau Password Kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        //TODO: Sign in with registered acc

    }
}