package com.example.wheremymoneyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class InputTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_transaction)

        val m_inputIncomeButton = findViewById<Button>(R.id.inputIncomeButton)

        m_inputIncomeButton.setOnClickListener {
            val m_itemForm = findViewById<EditText>(R.id.itemInput).text.toString()
            val m_priceForm = findViewById<EditText>(R.id.priceInput).text.toString().toDoubleOrNull()
        }
    }
}