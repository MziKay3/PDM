package com.example.e_banking
//RegisterActivity.kt
import android.os.Bundle
import android.util.Log//sterge dupa test
import android.widget.Button
import android.widget.EditText
import android.widget.Toast//sterge dupa test
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var usernameInputR : EditText
    lateinit var passwordInputR : EditText
    lateinit var RegisterButton : Button
    lateinit var phoneNumberInput : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        usernameInputR = findViewById(R.id.usernamer_input)
        passwordInputR = findViewById(R.id.passwordr_input)
        phoneNumberInput= findViewById(R.id.phonenumber_input)
        RegisterButton = findViewById(R.id.register_button)


        RegisterButton.setOnClickListener {
            val username = usernameInputR.text.toString()
            val password = passwordInputR.text.toString()
            val phoneNumber = phoneNumberInput.text.toString()
            //TODO: Register logic
            Log.i("Register", "Username: $username, Password: $password, Phone Number: $phoneNumber")
        }

    }
}