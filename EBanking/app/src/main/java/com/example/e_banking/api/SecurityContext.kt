package com.example.e_banking.api

object SecurityContext {

    var token: String = ""
        private set

    fun setToken(newTokenValue: String, accessor: SecurityContextAccessor) {
        token = newTokenValue
    }
}