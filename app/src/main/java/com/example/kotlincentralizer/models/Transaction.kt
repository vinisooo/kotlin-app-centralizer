package com.example.kotlincentralizer.models

import java.util.UUID

enum class TransactionType {
    IN, OUT
}
public class Transaction (
    val description: String,
    val value: Float,
    val type: TransactionType
) {
    val id: String = UUID.randomUUID().toString()
    override fun toString(): String {
        if (type == TransactionType.IN) return "$description                    R$$value"
        return "$description                    -R$$value"
    }
}