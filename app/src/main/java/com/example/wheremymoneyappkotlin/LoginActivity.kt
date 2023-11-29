package com.example.wheremymoneyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_design)

        val emailLogin = findViewById<EditText>(R.id.m_emailForm)
        val passLogin = findViewById<EditText>(R.id.m_passwordForm)
        val loginButton = findViewById<Button>(R.id.m_loginButton)

        //TODO: Get Database Login Information[email, password]
        //TODO: Check If Login Input is equal with the login information from database

        loginButton.setOnClickListener {
            setContentView(R.layout.activity_main)
        }



    }
}