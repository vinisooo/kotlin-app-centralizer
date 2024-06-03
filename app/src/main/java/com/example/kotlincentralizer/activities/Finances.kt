package com.example.kotlincentralizer.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlincentralizer.databinding.ActivityFinancesBinding
import com.example.kotlincentralizer.models.Transaction
import com.example.kotlincentralizer.models.TransactionType

class Finances : AppCompatActivity() {

    private var balance: Float = 0.0F
    private var transactions = ArrayList<Transaction>()

    private lateinit var binding: ActivityFinancesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinancesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTransaction.setOnClickListener{
            addTransaction()
        }
    }

    private fun addTransaction () {
        val transactionDescription = binding.transactionDescription.text
        val transactionValue = binding.transactionValue.text
        val transactionType = binding.transactionType.isChecked

        val transactionTypeBool = if(transactionType) TransactionType.IN else TransactionType.OUT

        if (transactionValue.isEmpty()) return

        val newTransaction = Transaction(transactionDescription.toString(), transactionValue.toString().toFloat(), transactionTypeBool)
        transactions.add(newTransaction)

         reloadTransactions()
    }

    private fun reloadTransactions () {
        balance = 0.0F
        for (value in transactions) {
            if (value.type == TransactionType.IN) {
                balance += value.value
            }
            else {
                balance -= value.value
            }
        }

        binding.balance.text = "R$$balance"

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transactions)
        binding.transactionsList.adapter = adapter
    }
}