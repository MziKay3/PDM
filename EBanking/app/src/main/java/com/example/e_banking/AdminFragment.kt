package com.example.e_banking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.text.InputType
import android.widget.TextView
import android.widget.Toast
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.SecurityContext
import com.example.e_banking.api.SecurityContextAccessor
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.dtos.UserDetails

class AdminFragment : Fragment(), SecurityContextAccessor {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin, container, false)

        val logoutButton = view.findViewById<Button>(R.id.logout_button)
        logoutButton.setOnClickListener {
            SecurityContext.logout(this)
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            // Curăță stiva de activități ca să nu poți reveni cu back
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
        val inputName = view.findViewById<EditText>(R.id.inputName)
        val viewEmail = view.findViewById<TextView>(R.id.inputEmail)
        val inputPhone = view.findViewById<EditText>(R.id.inputPhone)
        val inputPassword = view.findViewById<EditText>(R.id.inputPassword)

        val defaultCallback = DefaultCallback<UserDetails>(
            onSuccess = {
                response ->
                inputName.setText(response.name)
                viewEmail.text = response.email
                inputPhone.setText(response.phoneNumber)
                inputPassword.setText(response.password)
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
        APICaller.getUserDetails(defaultCallback)

        val btnEditName = view.findViewById<ImageButton>(R.id.btnEditName)
        val btnEditPhone = view.findViewById<ImageButton>(R.id.btnEditPhone)
        val btnEditPassword = view.findViewById<ImageButton>(R.id.btnEditPassword)

        btnEditName.setOnClickListener {
            inputName.isEnabled = true
            inputName.requestFocus()
        }

        btnEditPhone.setOnClickListener {
            inputPhone.isEnabled = true
            inputPhone.requestFocus()
        }

        btnEditPassword.setOnClickListener {
            inputPassword.isEnabled = true
            inputPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            inputPassword.requestFocus()
        }

        return view
    }
}