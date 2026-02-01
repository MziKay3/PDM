package com.example.e_banking
//RegisterActivity.kt
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.callbacks.DefaultCallback
import com.example.e_banking.api.dtos.RegisterDto

class RegisterActivity : AppCompatActivity() {
    lateinit var usernameInputR : EditText
    lateinit var passwordInputR : EditText
    lateinit var registerButton : Button
    lateinit var phoneNumberInput : EditText
    lateinit var nameInput : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        usernameInputR = findViewById(R.id.emailInput)
        passwordInputR = findViewById(R.id.passInput)
        phoneNumberInput= findViewById(R.id.phoneNumberInput)
        nameInput = findViewById(R.id.name_input)
        registerButton = findViewById(R.id.register_button)

        registerButton.setOnClickListener { onRegisterClick() }

    }

    fun onRegisterClick() {
        val name = nameInput.text.toString()
        val phoneNumber = phoneNumberInput.text.toString()
        val email = usernameInputR.text.toString()
        val password = passwordInputR.text.toString()

        if (name.isEmpty() || phoneNumber.isEmpty() ||
            email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completează toate câmpurile", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val registerDto = RegisterDto(
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            password = password
        )
        val callback = DefaultCallback<Unit>(
            onSuccess = {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                this.finish()
            },
            onFailure = {
                response ->
                response.errorBody()?.let {
                    val message = it.string()
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        )

        APICaller.register(registerDto, callback)

        Log.i("Register", "Username: $email, Password: $password, Phone Number: $phoneNumber")
    }
}