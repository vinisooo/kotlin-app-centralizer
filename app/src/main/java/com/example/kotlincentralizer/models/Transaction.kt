package com.example.kotlincentralizer.models

enum class TransactionType {
    IN, OUT
}
public class Transaction (
    val description: String,
    val value: Float,
    val type: TransactionType
) {
    override fun toString(): String {
        if (type == TransactionType.IN) return "$description                    R$$value"
        return "$description                    -R$$value"
    }
}