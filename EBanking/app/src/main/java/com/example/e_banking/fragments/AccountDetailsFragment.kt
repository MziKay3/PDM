package com.example.e_banking.fragments

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_banking.R
import com.example.e_banking.adapters.TransactionAdapter
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.dtos.AccountDetails
import com.example.e_banking.api.dtos.TransactionDto


class AccountDetailsFragment : Fragment() {
    private var isVisibleInfo=false
    lateinit var accountDetailsCallback: DefaultCallback<AccountDetails>
    lateinit var transactionsCallback: DefaultCallback<List<TransactionDto>>
    lateinit var accountIban: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val balanceText = view.findViewById<TextView>(R.id.balanceValue)
        val ibanText = view.findViewById<TextView>(R.id.iban)
        val toggleBtn = view.findViewById<ImageView>(R.id.toggleVisibility)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvTransactions)

        transactionsCallback = DefaultCallback(
            onSuccess = {
                transactions ->
                val adapter = TransactionAdapter(
                    items = transactions,
                    currentAccountIban = accountIban
                )
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = adapter
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

        accountDetailsCallback = DefaultCallback(
            onSuccess = {
                response ->
                balanceText.text = response.balance.toString()
                ibanText.text = response.iban
                accountIban = response.iban
                APICaller.getTransactions(transactionsCallback)
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

        toggleBtn.setImageResource(R.drawable.ic_visibility_off)
        balanceText.transformationMethod = PasswordTransformationMethod.getInstance()
        ibanText.transformationMethod = PasswordTransformationMethod.getInstance()

        toggleBtn.setOnClickListener {
            isVisibleInfo = !isVisibleInfo
            if (isVisibleInfo) {
                APICaller.getAccountDetails(accountDetailsCallback)
                balanceText.transformationMethod = null
                ibanText.transformationMethod = null
                toggleBtn.setImageResource(R.drawable.ic_visibility)
            } else {
                balanceText.transformationMethod = PasswordTransformationMethod.getInstance()
                ibanText.transformationMethod = PasswordTransformationMethod.getInstance()
                toggleBtn.setImageResource(R.drawable.ic_visibility_off)
            }
        }

        return view
    }
}


