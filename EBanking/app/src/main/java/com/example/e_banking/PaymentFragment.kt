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

class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        val chipNew = view.findViewById<Chip>(R.id.chipNewPayment)
        val chipRecurring = view.findViewById<Chip>(R.id.chipRecurring)
        val containerFrame = view.findViewById<FrameLayout>(R.id.paymentContentContainer)

        // Inflate layouts pentru fiecare secțiune
        val newPaymentView = inflater.inflate(R.layout.payment_new, containerFrame, false)
        val recurringPaymentView = inflater.inflate(R.layout.payment_recurring, containerFrame, false)

        // La început afișăm “Plată nouă”
        containerFrame.removeAllViews()
        containerFrame.addView(newPaymentView)
        chipNew.isChecked = true
        chipRecurring.isChecked = false

        // Click pe chip-uri
        chipNew.setOnClickListener {
            containerFrame.removeAllViews()
            containerFrame.addView(newPaymentView)
            chipNew.isChecked = true
            chipRecurring.isChecked = false
        }

        chipRecurring.setOnClickListener {
            containerFrame.removeAllViews()
            containerFrame.addView(recurringPaymentView)
            chipRecurring.isChecked = true
            chipNew.isChecked = false
        }

        return view
    }
}