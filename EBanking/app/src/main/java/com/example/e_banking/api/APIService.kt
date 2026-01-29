package com.example.e_banking.api

import retrofit2.http.GET
import retrofit2.Call

interface APIService {

    @GET("api/test")
    fun test(): Call<String>
}