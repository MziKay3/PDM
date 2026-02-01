package com.example.e_banking.api.dtos

data class User(val id: String, val email: String, val password: String)

data class UserDetails(val name:String, val phoneNumber: String, val email: String, val password: String)

data class UpdateUserDetails(val name:String, val phoneNumber: String, val password: String)