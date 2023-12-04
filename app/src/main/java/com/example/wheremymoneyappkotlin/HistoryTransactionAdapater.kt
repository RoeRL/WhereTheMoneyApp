package com.example.wheremymoneyappkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class HistoryTransactionAdapater(private val transaction: ArrayList<HistoryTransaction>,private val transactionKeys: ArrayList<String>, private val onItemClick: (HistoryTransaction, String) -> Unit) :
    RecyclerView.Adapter<HistoryTransactionAdapater.TransactionHolder>() {
    class TransactionHolder(view: View): RecyclerView.ViewHolder(view){
        val itemLabel:TextView = view.findViewById(R.id.label)
        val itemValue:TextView = view.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_history, parent, false)
        return TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction = transaction[position]
        val key = transactionKeys[position]
        val context = holder.itemValue.context

        if (transaction.itemValue >= 0){
            holder.itemValue.text = "$%.2f".format(abs(transaction.itemValue))
            holder.itemValue.setTextColor(ContextCompat.getColor(context, R.color.green))
        }else{
            holder.itemValue.text = "$%.2f".format(transaction.itemValue)
            holder.itemValue.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        holder.itemLabel.text= transaction.item

        holder.itemView.setOnClickListener {
            onItemClick(transaction, key)
        }
    }

    override fun getItemCount(): Int {
        return transaction.size
    }
}