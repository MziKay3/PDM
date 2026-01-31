package com.example.e_banking.api

import com.example.e_banking.api.dtos.LoginDto
import com.example.e_banking.api.dtos.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitAPIDefinition {

    @GET("api/test")
    fun test(): Call<String>

    @GET("api/test/users")
    fun test(@Header("Authorization") authHeaderValue: String): Call<List<User>>

    @POST("api/auth/login")
    fun login(@Body loginDto: LoginDto): Call<String>
}