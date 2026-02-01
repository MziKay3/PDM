package com.example.e_banking

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.dtos.AccountDetails
import com.example.e_banking.api.dtos.PaymentRequest
import kotlin.properties.Delegates

class NewPaymentFragment : Fragment(R.layout.payment_new) {

    private var balance by Delegates.notNull<Float>()
    private var selectedRecurrence: Recurrence = Recurrence.NONE

    private lateinit var fromTextView: TextView
    private lateinit var beneficiaryNameEditText: EditText
    private lateinit var beneficiaryAccountEditText: EditText
    private lateinit var paymentAmountEditText: EditText
    private lateinit var paymentDetailsEditText: EditText
    private lateinit var recurrenceSpinner: Spinner
    private lateinit var continueButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fromTextView = view.findViewById(R.id.fromAccountNumber)
        beneficiaryNameEditText = view.findViewById(R.id.beneficiaryName)
        beneficiaryAccountEditText = view.findViewById(R.id.beneficiaryAccount)
        paymentAmountEditText = view.findViewById(R.id.paymentAmount)
        paymentDetailsEditText = view.findViewById(R.id.paymentDetails)
        recurrenceSpinner = view.findViewById(R.id.recurrenceSpinner)
        continueButton = view.findViewById(R.id.btnContinue)

        val accountDetailsCallback = DefaultCallback<AccountDetails>(
            onSuccess = {
                response ->
                fromTextView.text = response.iban
                balance = response.balance
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
        APICaller.getAccountDetails(accountDetailsCallback)

        val recurrenceValues = Recurrence.entries.toTypedArray()
        recurrenceSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long) {
                    selectedRecurrence = recurrenceValues[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        val oneTimePaymentCallback = DefaultCallback<Unit>(
            onSuccess = {
                _ ->
                clearFields()
                Toast.makeText(
                    requireContext(),
                    "Payment was successful",
                    Toast.LENGTH_LONG
                )
                    .show()
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

        continueButton.setOnClickListener {
            if (selectedRecurrence == Recurrence.NONE) {
                val fromAccount = fromTextView.text.toString()
                val toAccount = beneficiaryAccountEditText.text.toString()
                val toAccountName = beneficiaryNameEditText.text.toString()
                val details = paymentDetailsEditText.text.toString()
                val amount = paymentAmountEditText.text.toString().toFloat()

                val oneTimePaymentRequest = PaymentRequest(
                    fromIban = fromAccount,
                    toIban = toAccount,
                    toAccountName = toAccountName,
                    amount = amount,
                    details = details
                )
                APICaller.makeOneTimePayment(oneTimePaymentRequest, oneTimePaymentCallback)
            }
        }
    }

    private fun clearFields() {
        beneficiaryNameEditText.setText("")
        beneficiaryAccountEditText.setText("")
        paymentAmountEditText.setText("")
        paymentDetailsEditText.setText("")
        recurrenceSpinner.setSelection(0)
    }
}
