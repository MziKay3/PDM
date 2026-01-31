package com.example.e_banking.api

import com.example.e_banking.api.dtos.LoginDto
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.POST

interface APIService {

    @GET("api/test")
    fun test(): Call<String>

    @POST("api/auth/login")
    fun login(loginDto: LoginDto): Call<String>
}