package com.example.e_banking.api.dtos

data class TransactionDto(
    val senderIban: String,
    val receiverIban: String,
    val amount: Float)