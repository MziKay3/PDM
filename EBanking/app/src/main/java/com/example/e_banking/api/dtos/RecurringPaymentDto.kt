package com.example.e_banking.api.dtos

import java.util.Date

data class RecurringPaymentDto(
    val id: Int,
    val receiverIban: String,
    val receiverAccountName: String,
    val nextPayment: Date,
    val recurrency: String,
    val amount: Float)
