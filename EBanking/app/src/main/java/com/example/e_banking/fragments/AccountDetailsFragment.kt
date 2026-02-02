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
import com.example.e_banking.R
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.dtos.AccountDetails


class AccountDetailsFragment : Fragment() {
    private var isVisibleInfo=false
    lateinit var accountDetailsCallback: DefaultCallback<AccountDetails>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val balanceText = view.findViewById<TextView>(R.id.balanceValue)
        val ibanText = view.findViewById<TextView>(R.id.iban)
        val toggleBtn = view.findViewById<ImageView>(R.id.toggleVisibility)

        accountDetailsCallback = DefaultCallback(
            onSuccess = {
                response ->
                balanceText.text = response.balance.toString()
                ibanText.text = response.iban
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
}


