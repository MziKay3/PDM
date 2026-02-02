package com.example.e_banking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_banking.api.dtos.RecurringPaymentDto
import java.util.Date

class RecurringPaymentFragment : Fragment(R.layout.payment_recurring) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val payments = mutableListOf(
            RecurringPaymentDto(
                id = 1,
                receiverIban = "RO49AAAA...",
                receiverAccountName = "Orange",
                nextPayment = Date(),
                recurrency = "Lunar",
                amount = 45f))

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvRecurringPayments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecurringPaymentAdapter(payments,
            onDeleteClick = {
                paymentDto -> {}
            })
    }
}
