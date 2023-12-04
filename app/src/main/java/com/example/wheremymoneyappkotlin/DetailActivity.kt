package com.example.wheremymoneyappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val databaseReference = FirebaseDatabase.getInstance().getReference("transactions")

        val selectedTransaction = intent.getParcelableExtra<HistoryTransaction>("TRANSACTION")
        val transactionKey = intent.getStringExtra("TRANSACTION_KEY")

        // Find views in the layout
        val labelInput = findViewById<EditText>(R.id.labelInput)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val updateButton = findViewById<Button>(R.id.updateBtn)
        val closeButton = findViewById<ImageButton>(R.id.closeBtn)

        // Display transaction details in the layout
        labelInput.setText(selectedTransaction?.item)
        amountInput.setText(selectedTransaction?.itemValue.toString())

        // Handle update button click
        updateButton.setOnClickListener {
            val updatedLabel = labelInput.text.toString()
            val updatedAmount = amountInput.text.toString().toDoubleOrNull() ?: 0.0

            selectedTransaction?.let {
                val updatedTransaction = HistoryTransaction(updatedLabel, updatedAmount, transactionKey)

                if (transactionKey != null) {
                    databaseReference.child(transactionKey).setValue(updatedTransaction)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "Transaction updated successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Failed to update transaction",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }

            closeButton.setOnClickListener {
                finish()
            }

            // Handle close button click
            val closeButton = findViewById<ImageButton>(R.id.closeBtn)
            closeButton.setOnClickListener {
                finish()
            }
        }
    }
}
