package com.example.e_banking.api.callbacks

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultCallback<T>(
    val onSuccess: ((T) -> Unit)? = null,
    val onFailure: ((response: Response<T?>) -> Unit)? = null)
    : Callback<T> {

    private val unauthorized = 401

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (response.code() == unauthorized) {
            Log.e("Error", "Unauthorized call")
            return
        }

        if (response.isSuccessful) {
            response.body()?.let {
                onSuccess?.invoke(it)
            }
        } else {
            val code = response.code()
            onFailure?.invoke(response)
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        throw t
    }
}