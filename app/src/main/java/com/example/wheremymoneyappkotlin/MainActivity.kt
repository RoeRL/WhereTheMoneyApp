package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    private lateinit var transactions : ArrayList<HistoryTransaction>
    private lateinit var adapter: HistoryTransactionAdapater
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var transactionKeys : ArrayList<String>

    private val databaseReferense = FirebaseDatabase.getInstance().getReference("transactions")

    private lateinit var recyclerView: RecyclerView
    private lateinit var balanceValue: TextView
    private lateinit var incomeValue: TextView
    private lateinit var expenseValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions = ArrayList()
        transactionKeys = ArrayList()



        linearLayoutManager = LinearLayoutManager(this)

        databaseReferense.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val fetchedTransactions = ArrayList<HistoryTransaction>()


                for (snapshot in dataSnapshot.children) {
                    val items = snapshot.child("item").getValue(String::class.java) ?: ""
                    val price = snapshot.child("price").getValue(Double::class.java) ?: 0.0
                    val key = snapshot.key ?: "" // Get the key

                    fetchedTransactions.add(HistoryTransaction(items, price, key))
                    transactionKeys.add(key)
                }

                transactions.clear()
                transactions.addAll(fetchedTransactions)



                recyclerView.adapter = adapter

                updateDashboard()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        adapter = HistoryTransactionAdapater(transactions, transactionKeys) { selectedTransaction, transactionKey ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("TRANSACTION", selectedTransaction)
            intent.putExtra("TRANSACTION_KEY", transactionKey)
            startActivity(intent)
        }


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        val incomeButton = findViewById<Button>(R.id.incomeButton)
        val expenseButton = findViewById<Button>(R.id.expenseButton)

        expenseButton.setOnClickListener {
            val intent = Intent(this, ExpenseTransactionActivity::class.java)
            startActivity(intent)
        }
        incomeButton.setOnClickListener {
            val intent = Intent(this, InputTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun formatBalance(value: Double): String {
        val suffixes = arrayOf("", "k", "m", "b", "t") // Add more if needed

        var newValue = value
        var magnitude = 0

        while (newValue >= 100000 || newValue <= -100000) {
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