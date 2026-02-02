package com.example.e_banking.api.dtos

data class RecurringPaymentDto(
    val id: Int,
    val receiverIban: String,
    val receiverAccountName: String,
    val nextPayment: String,
    val recurrency: String,
    val amount: Float)
