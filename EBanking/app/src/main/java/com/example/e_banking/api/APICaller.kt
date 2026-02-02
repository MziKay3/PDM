package com.example.e_banking.api

import android.content.Context
import android.widget.Toast
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.callbacks.LoginCallback
import com.example.e_banking.api.dtos.AccountDetails
import com.example.e_banking.api.dtos.LoginDto
import com.example.e_banking.api.dtos.OneTimePaymentRequest
import com.example.e_banking.api.dtos.RecurringPaymentDto
import com.example.e_banking.api.dtos.RecurringPaymentRequest
import com.example.e_banking.api.dtos.RegisterDto
import com.example.e_banking.api.dtos.TransactionDto
import com.example.e_banking.api.dtos.UpdateUserDetails
import com.example.e_banking.api.dtos.UserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object APICaller {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5131/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: RetrofitAPIDefinition = retrofit.create(RetrofitAPIDefinition::class.java)

    fun test(context: Context) {
        api.test().enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(context, "success!", Toast.LENGTH_LONG).show()
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(context, "not success!", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    fun login(loginDto: LoginDto, loginCallback: LoginCallback) {
        api.login(loginDto).enqueue(loginCallback)
    }

    fun register(registerDto: RegisterDto, registerCallback: DefaultCallback<Unit>) {
        api.register(registerDto).enqueue(registerCallback)
    }

    fun getUserDetails(callback: DefaultCallback<UserDetails>) {
        api.getUserDetails(SecurityContext.authHeaderValue).enqueue(callback)
    }

    fun updateUserDetails(
        updateUserDetails: UpdateUserDetails,
        callback: DefaultCallback<Unit>) {
        api.updateUserDetails(
            SecurityContext.authHeaderValue,
            updateUserDetails)
            .enqueue(callback)
    }

    fun getAccountDetails(callback: DefaultCallback<AccountDetails>) {
        api.getAccountDetails(SecurityContext.authHeaderValue)
            .enqueue(callback)
    }

    fun makeOneTimePayment(
        oneTimePaymentRequest: OneTimePaymentRequest,
        callback: DefaultCallback<Unit>) {
        api.makeOneTimePayment(
            SecurityContext.authHeaderValue,
            oneTimePaymentRequest)
            .enqueue(callback)
    }

    fun makeRecurringPayment(
        paymentRequest: RecurringPaymentRequest,
        callback: DefaultCallback<Unit>) {
        api.makeRecurringPayment(
            SecurityContext.authHeaderValue,
            paymentRequest)
            .enqueue(callback)
    }

    fun deleteRecurringPayment(
        paymentId: Int,
        callback: DefaultCallback<Unit>) {
        api.deleteRecurringPayment(
            SecurityContext.authHeaderValue,
            paymentId)
            .enqueue(callback)
    }

    fun getTransactions(callback: DefaultCallback<List<TransactionDto>>) {
        api.getTransactions(SecurityContext.authHeaderValue)
            .enqueue(callback)
    }

    fun getRecurringPayments(callback: DefaultCallback<List<RecurringPaymentDto>>) {
        api.getRecurringPayments(SecurityContext.authHeaderValue)
            .enqueue(callback)
    }
}