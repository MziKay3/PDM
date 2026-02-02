package com.example.e_banking.api.dtos

data class OneTimePaymentRequest(
    val fromIban: String,
    val toAccountName: String,
    val toIban: String,
    val amount: Float,
    val details: String)

data class RecurringPaymentRequest(
    val fromIban: String,
    val toAccountName: String,
    val toIban: String,
    val amount: Float,
    val details: String,
    val recurrency: String)
