package com.example.wheremymoneyappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var transactions : ArrayList<HistoryTransaction>
    private lateinit var adapter: HistoryTransactionAdapater
    private lateinit var linearLayoutManager : LinearLayoutManager

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions = arrayListOf(
            HistoryTransaction("Pisang", -40000.00),
            HistoryTransaction("Duit", 40000.00),
            HistoryTransaction("Gedhang", -90000.00)

        )

        adapter = HistoryTransactionAdapater(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }
}