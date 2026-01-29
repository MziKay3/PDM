package com.example.e_banking.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5131/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(APIService::class.java);
}