package com.example.e_banking.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCallback : Callback<String>, SecurityContextAccessor {

    private val unauthorized = 403

    override fun onResponse(
        call: Call<String?>,
        response: Response<String?>) {

        if (response.code() == unauthorized)
            return

        if (response.isSuccessful)
        {
            val token = response.body()
            if (token == null)
                return

            SecurityContext.setToken(token, this)
        }
    }

    override fun onFailure(call: Call<String?>, t: Throwable) {
        throw t
    }
}