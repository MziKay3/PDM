package com.example.e_banking.api.dtos

data class PaymentRequest(
    val fromIban: String,
    val toAccountName: String,
    val toIban: String,
    val amount: Float,
    val details: String)
