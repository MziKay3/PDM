package com.example.e_banking.api

import com.example.e_banking.api.dtos.AccountDetails
import com.example.e_banking.api.dtos.LoginDto
import com.example.e_banking.api.dtos.OneTimePaymentRequest
import com.example.e_banking.api.dtos.RecurringPaymentDto
import com.example.e_banking.api.dtos.RecurringPaymentRequest
import com.example.e_banking.api.dtos.RegisterDto
import com.example.e_banking.api.dtos.TransactionDto
import com.example.e_banking.api.dtos.UpdateUserDetails
import com.example.e_banking.api.dtos.User
import com.example.e_banking.api.dtos.UserDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RetrofitAPIDefinition {

    @GET("api/test")
    fun test(): Call<String>

    @GET("api/test/users")
    fun test(@Header("Authorization") authHeaderValue: String): Call<List<User>>

    @POST("api/auth/login")
    fun login(@Body loginDto: LoginDto): Call<String>

    @POST("api/auth/register")
    fun register(@Body registerDto: RegisterDto): Call<Unit>

    @GET("api/user/user-details")
    fun getUserDetails(@Header("Authorization") authHeaderValue: String):
            Call<UserDetails>

    @PUT("api/user/user-details")
    fun updateUserDetails(
        @Header("Authorization") authHeaderValue: String,
        @Body updateUserDetails: UpdateUserDetails
    ): Call<Unit>

    @GET("api/account/account-details")
    fun getAccountDetails(@Header("Authorization") authHeaderValue: String):
            Call<AccountDetails>

    @GET("api/account/transactions")
    fun getTransactions(@Header("Authorization") authHeaderValue: String):
            Call<List<TransactionDto>>

    @POST("api/payment/one-time")
    fun makeOneTimePayment(
        @Header("Authorization") authHeaderValue: String,
        @Body oneTimePaymentRequest: OneTimePaymentRequest
    ): Call<Unit>

    @POST("api/payment/recurring")
    fun makeRecurringPayment(
        @Header("Authorization") authHeaderValue: String,
        @Body paymentRequest: RecurringPaymentRequest
    ): Call<Unit>

    @DELETE("api/payment/recurring-payment/{id}")
    fun deleteRecurringPayment(
        @Header("Authorization") authHeaderValue: String,
        @Path("id") paymentId: Int
    ): Call<Unit>

    @GET("api/payment/recurring-payments")
    fun getRecurringPayments(
        @Header("Authorization") authHeaderValue: String
    ): Call<List<RecurringPaymentDto>>
}