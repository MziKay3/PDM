package com.example.e_banking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class HomeFragment : Fragment() {
    private var isVisibleInfo=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val balanceText = view.findViewById<TextView>(R.id.ValoareBalanta)
        val ibanText = view.findViewById<TextView>(R.id.iban)
        val toggleBtn = view.findViewById<ImageView>(R.id.toggleVisibility)

        // Date reale (aici pui tu ce vrei)
        val realBalance = "15230.55 RON"
        val realIban = "RO49AAAA1B31007593840000"

        // La început ASCUNSE (cu stelute)
        balanceText.text = "******"
        ibanText.text = "***********************"
        toggleBtn.setImageResource(R.drawable.ic_visibility_off)

        toggleBtn.setOnClickListener {
            isVisibleInfo = !isVisibleInfo

            if (isVisibleInfo) {
                // Afișează datele reale
                balanceText.text = realBalance
                ibanText.text = realIban
                toggleBtn.setImageResource(R.drawable.ic_visibility)
            } else {
                // Ascunde cu stelute
                balanceText.text = "******"
                ibanText.text = "***********************"
                toggleBtn.setImageResource(R.drawable.ic_visibility_off)
            }
        }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
}


