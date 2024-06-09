package com.example.kotlincentralizer.activities

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlincentralizer.databinding.ActivityFinancesBinding
import com.example.kotlincentralizer.databinding.TransactionDialogBinding
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

        binding.transactionsList.setOnItemClickListener { parent, view, position, id ->
            showTransactionDialog(transactions[position])
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
        emptyForm()
        Toast.makeText(this, "Transaction added", Toast.LENGTH_SHORT)
    }

    private fun emptyForm () {
        binding.transactionDescription.setText("")
        binding.transactionValue.setText("")
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

    private fun showTransactionDialog (transaction: Transaction) {
        val dialogBinding = TransactionDialogBinding.inflate(layoutInflater)

        dialogBinding.dialogTransactionDescription.text = "Do you really want to delete transaction ${transaction.description}?"

        AlertDialog.Builder(this)
            .setTitle("Delete transaction")
            .setPositiveButton("Delete") { _, _ -> deleteTransaction(transaction.id) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteTransaction(uuid: String) {
        val transactionToRemove = transactions.find { it.id == uuid }
        transactionToRemove?.let { transactions.remove(it) }

        reloadTransactions()

        Toast.makeText(this, "Transaction successfully deleted", Toast.LENGTH_SHORT)
    }

}