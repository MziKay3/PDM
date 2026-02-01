package com.example.e_banking.api

import com.example.e_banking.AdminFragment

object SecurityContext {

    private var token: String = ""

    val authHeaderValue
        get() = "Bearer $token"

    fun setToken(newTokenValue: String, accessor: SecurityContextAccessor) {
        token = newTokenValue
    }

    fun logout(accessor: SecurityContextAccessor) {
        token = ""
    }
}