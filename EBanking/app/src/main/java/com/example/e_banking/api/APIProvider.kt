package com.example.e_banking.api

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object APIProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5131/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIService = retrofit.create(APIService::class.java)

    fun test(context: Context) {
        api.test().enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val responseBody = response.body();
                    Toast.makeText(context, "success!", Toast.LENGTH_LONG).show()
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    val someValue = "";
                    Toast.makeText(context, "not succes!", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}