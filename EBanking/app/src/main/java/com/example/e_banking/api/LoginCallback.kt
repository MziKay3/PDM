package com.example.e_banking.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCallback(val onLoginSuccess: (() -> Unit)? = null, val onLoginFailure: (() -> Unit)? = null) : Callback<String>, SecurityContextAccessor {
    private val unauthorized = 403

    override fun onResponse(
        call: Call<String?>,
        response: Response<String?>) {

        if (response.code() == unauthorized) {
            onLoginFailure?.invoke()
            return
        }

        if (!response.isSuccessful) {
            onLoginFailure?.invoke()
            return
        }

        val token = response.body()
        if (token == null) {
            onLoginFailure?.invoke()
            return
        }

        onLoginSuccess?.invoke()
        SecurityContext.setToken(token, this)
    }

    override fun onFailure(call: Call<String?>, t: Throwable) {
        throw t
    }
}