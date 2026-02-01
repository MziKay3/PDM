package com.example.e_banking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import android.widget.TextView

class PaymentFragment : Fragment(R.layout.fragment_payment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chipNew = view.findViewById<Chip>(R.id.chipNewPayment)
        val chipRecurring = view.findViewById<Chip>(R.id.chipRecurring)

        if (savedInstanceState == null) {
            showNewPayment()
            chipNew.isChecked = true
        }

        chipNew.setOnClickListener {
            showNewPayment()
            chipNew.isChecked = true
            chipRecurring.isChecked = false
        }

        chipRecurring.setOnClickListener {
            showRecurringPayment()
            chipRecurring.isChecked = true
            chipNew.isChecked = false
        }
    }

    private fun showNewPayment() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.paymentContentContainer,
                NewPaymentFragment()
            )
            .commit()
    }

    private fun showRecurringPayment() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.paymentContentContainer,
                RecurringPaymentFragment()
            )
            .commit()
    }
}
