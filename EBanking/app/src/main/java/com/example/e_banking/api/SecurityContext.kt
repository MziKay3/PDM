package com.example.e_banking.api

object SecurityContext {

    private var token: String = ""

    val authHeaderValue
        get() = "Bearer $token"

    fun setToken(newTokenValue: String, accessor: SecurityContextAccessor) {
        token = newTokenValue
    }
}