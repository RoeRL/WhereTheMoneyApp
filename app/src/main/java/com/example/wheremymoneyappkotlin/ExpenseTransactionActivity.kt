package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.database.FirebaseDatabase

class ExpenseTransactionActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_transaction)

        database = FirebaseDatabase.getInstance()


        val m_itemLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.itemLayout)
        val m_priceLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.priceLayout)
        val m_inputIncomeButton = findViewById<Button>(R.id.inputIncomeButton)
        val m_closeIncomeButton = findViewById<ImageButton>(R.id.closeIncomeTransaction)

        m_closeIncomeButton.setOnClickListener {
            finish()
        }

        m_inputIncomeButton.setOnClickListener {

            val m_itemForm = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.itemInput).text.toString()
            val m_priceForm = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.priceInput).text.toString().toDoubleOrNull()
            if (m_itemForm.isEmpty()) {
                m_itemLayout.error = "Please input your item"
                return@setOnClickListener
            }
            if (m_priceForm==null) {
                m_priceLayout.error = "Please input your price"
                return@setOnClickListener
            }

            if (m_priceForm != null) {
                writeToFirebase(m_itemForm, -m_priceForm)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun writeToFirebase(item: String, price: Double){
        val databaseReference = database.reference.child("transactions")
        val transactionId = databaseReference.push().key
        val transactionData = hashMapOf("item" to item, "price" to price)

        if (transactionId != null) {
            databaseReference.child(transactionId).setValue(transactionData)
        }
    }
}