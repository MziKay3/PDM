package com.example.e_banking.api.callbacks

import com.example.e_banking.api.SecurityContext
import com.example.e_banking.api.SecurityContextAccessor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCallback(val onLoginSuccess: (() -> Unit)? = null, val onLoginFailure: (() -> Unit)? = null)
    : Callback<String>, SecurityContextAccessor {
    private val unauthorized = 401

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

        SecurityContext.setToken(token, this)
        onLoginSuccess?.invoke()
    }

    override fun onFailure(call: Call<String?>, t: Throwable) {
        throw t
    }
}