package com.example.wheremymoneyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val increaseButton = findViewById<Button>(R.id.incomeButton)
        val expenseButton = findViewById<Button>(R.id.expenseButton)

        val valueMoney = findViewById<TextView>(R.id.moneyValue)

        //Get initMoney Data from database:
        var initMoney = 2000


        valueMoney.text = initMoney.toString()
        increaseButton.setOnClickListener {
            initMoney += 200
            //input Data to Database
            valueMoney.text = initMoney.toString()
        }

        expenseButton.setOnClickListener {
            initMoney -= 100
            //Input Data to database
            valueMoney.text = initMoney.toString()
        }





        //TODO: Make a Input Button Function and Expense
        //TODO: Layout For Input Data, Expense Data

    }
}