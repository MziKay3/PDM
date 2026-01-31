package com.example.e_banking.api.dtos

data class RegisterDto(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val password: String
)