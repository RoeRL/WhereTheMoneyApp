package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class InputTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_transaction)

        val m_itemForm = findViewById<EditText>(R.id.itemInput).text.toString()
        val m_priceForm = findViewById<EditText>(R.id.priceInput).text.toString().toDoubleOrNull()
        val m_itemLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.itemLayout)
        val m_priceLayout = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.priceLayout)
        val m_inputIncomeButton = findViewById<Button>(R.id.inputIncomeButton)
        val m_closeIncomeButton = findViewById<ImageButton>(R.id.closeIncomeTransaction)

        m_closeIncomeButton.setOnClickListener {
            finish()
        }

        m_inputIncomeButton.setOnClickListener {


            if (m_itemForm.isEmpty()) m_itemLayout.error = "Please input your item"
            if (m_priceForm==null) m_priceLayout.error = "Please input your item"
        }
    }
}