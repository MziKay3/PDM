package com.example.e_banking

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
import com.example.e_banking.api.dtos.UpdateUserDetails
import com.example.e_banking.api.dtos.UserDetails

class UserDetailsFragment : Fragment(), SecurityContextAccessor {
    lateinit var initialPhone: String
    lateinit var initialName: String
    lateinit var initialPassword: String
    lateinit var onUserDetailsGetCallback: DefaultCallback<UserDetails>

    private lateinit var btnEditName: ImageButton
    private lateinit var btnEditPhone: ImageButton
    private lateinit var btnEditPassword: ImageButton
    private lateinit var btnUpdate: Button

    private lateinit var inputName: EditText
    private lateinit var viewEmail: TextView
    private lateinit var inputPhone: EditText
    private lateinit var inputPassword: EditText

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
        inputName = view.findViewById(R.id.inputName)
        viewEmail = view.findViewById(R.id.inputEmail)
        inputPhone = view.findViewById(R.id.inputPhone)
        inputPassword = view.findViewById(R.id.inputPassword)
        btnEditName = view.findViewById(R.id.btnEditName)
        btnEditPhone = view.findViewById(R.id.btnEditPhone)
        btnEditPassword = view.findViewById(R.id.btnEditPassword)
        btnUpdate = view.findViewById(R.id.update_user_details_button)

        onUserDetailsGetCallback = getUserDetailsCallBack()
        APICaller.getUserDetails(onUserDetailsGetCallback)

        btnUpdate.setOnClickListener {
            val name = if (inputName.isEnabled) inputName.text.toString()
            else initialName
            val phoneNumber = if (inputPhone.isEnabled) inputPhone.text.toString()
            else initialPhone
            val password = if (inputPassword.isEnabled) inputPassword.text.toString()
            else initialPassword
            val updateUserDetails = UpdateUserDetails(
                name = name,
                phoneNumber = phoneNumber,
                password = password
            )
            APICaller.updateUserDetails(updateUserDetails, getUpdateCallBack())
        }

        btnEditName.setOnClickListener {
            if (!inputName.isEnabled) {
                inputName.requestFocus()
            } else {
                inputName.setText(initialName)
            }
            inputName.isEnabled = !inputName.isEnabled
        }

        btnEditPhone.setOnClickListener {
            if (!inputPhone.isEnabled) {
                inputPhone.requestFocus()
            } else {
                inputPhone.setText(initialPhone)
            }
            inputPhone.isEnabled = !inputPhone.isEnabled
        }

        btnEditPassword.setOnClickListener {
            if (!inputPassword.isEnabled) {
                inputPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                inputPassword.requestFocus()
            } else {
                inputPassword.setText(initialPassword)
                inputPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            inputPassword.isEnabled = !inputPassword.isEnabled
        }

        return view
    }

    private fun getUserDetailsCallBack() : DefaultCallback<UserDetails> {
        return DefaultCallback(
            onSuccess = {
                response ->

                inputName.isEnabled = false
                inputPhone.isEnabled = false
                inputPassword.isEnabled = false

                inputName.setText(response.name)
                initialName = response.name

                viewEmail.text = response.email

                inputPhone.setText(response.phoneNumber)
                initialPhone = response.phoneNumber

                inputPassword.setText(response.password)
                initialPassword = response.password
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
    }

    private fun getUpdateCallBack() : DefaultCallback<Unit> {
        return DefaultCallback(
            onSuccess = {
                _ ->
                APICaller.getUserDetails(onUserDetailsGetCallback)
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
    }
}