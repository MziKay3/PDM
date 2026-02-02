package com.example.e_banking.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_banking.R
import com.example.e_banking.adapters.RecurringPaymentAdapter
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.dtos.RecurringPaymentDto

class RecurringPaymentFragment : Fragment(R.layout.payment_recurring) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvRecurringPayments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val callback = DefaultCallback<List<RecurringPaymentDto>>(
            onSuccess = {
                response ->
                recyclerView.adapter = RecurringPaymentAdapter(
                    response.toMutableList(),
                    onDeleteClick = {
                        paymentDto ->
                        APICaller.deleteRecurringPayment(
                            paymentDto.id,
                            DefaultCallback<Unit>())
                    })
            },
            onFailure = {
                response ->
                response.errorBody()?.let {
                    val message = it.string()
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        )
        APICaller.getRecurringPayments(callback)
    }
}
