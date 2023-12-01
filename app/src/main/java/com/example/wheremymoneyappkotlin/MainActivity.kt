package com.example.wheremymoneyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private lateinit var transactions : ArrayList<HistoryTransaction>
    private lateinit var adapter: HistoryTransactionAdapater
    private lateinit var linearLayoutManager : LinearLayoutManager

    private lateinit var recyclerView: RecyclerView
    private lateinit var balanceValue: TextView
    private lateinit var incomeValue: TextView
    private lateinit var expenseValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions = arrayListOf(
            HistoryTransaction("Pisang", -40000.00),
            HistoryTransaction("Duit", 40000.00),
            HistoryTransaction("Gedhang", -90000.00),
            HistoryTransaction("Jualan", 30000.00),
            HistoryTransaction("Nasipadang", 200000.00),

        )

        adapter = HistoryTransactionAdapater(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        updateDashboard()

    }

    private fun formatBalance(value: Double): String {
        val suffixes = arrayOf("", "k", "m", "b", "t") // Add more if needed

        var newValue = value
        var magnitude = 0

        while (newValue >= 1000 || newValue <= -1000) {
            newValue /= 1000
            magnitude++
        }

        return if (magnitude < suffixes.size) {
            "%.0f%s".format(newValue, suffixes[magnitude])
        } else {
            "%.0f".format(value)
        }
    }

    private fun updateDashboard(){
        val totalBalance : Double = transactions.sumOf { it.itemValue }
        val incomeBalance : Double = transactions.filter { it.itemValue > 0 }.sumOf { it.itemValue }
        val expenseBalance : Double = totalBalance - incomeBalance

        balanceValue = findViewById(R.id.balanceValue)
        incomeValue = findViewById(R.id.incomeValue)
        expenseValue = findViewById(R.id.expenseValue)


        balanceValue.text = formatBalance(totalBalance)
        incomeValue.text = formatBalance(incomeBalance)
        expenseValue.text = formatBalance(expenseBalance)
    }
}