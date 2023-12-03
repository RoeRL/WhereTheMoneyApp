package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var emailLogin: EditText
    private lateinit var passLogin: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance()

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
        val databaseReference = database.reference.child("user_list")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var doneLogin = false



                for (snapshot in dataSnapshot.children){
                    val userEmail = snapshot.child("email").getValue(String::class.java)
                    val userPassword = snapshot.child("password").getValue(String::class.java)

                    if (emailLogin.text.toString() == userEmail && passLogin.text.toString() == userPassword) {
                        doneLogin = true
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                if (!doneLogin) Toast.makeText(this@LoginActivity, "Username and Password don't match", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Database side Error!", Toast.LENGTH_LONG).show()
            }
        })

    }
}