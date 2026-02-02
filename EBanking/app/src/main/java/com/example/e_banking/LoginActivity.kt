package com.example.e_banking
//LoginActivity.kt
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_banking.api.APICaller
import com.example.e_banking.api.callbacks.LoginCallback
import com.example.e_banking.api.dtos.LoginDto

class LoginActivity : AppCompatActivity() {
    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button
    lateinit var registerLButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.login_button)
        registerLButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
//            val username = usernameInput.text.toString()
//            val password = passwordInput.text.toString()
            val username = "admin@gmail.com"
            val password = "pass"

            Log.i("Login", "Username: $username, Password: $password")

//            if (username.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Completează toate câmpurile", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            val loginCallback = LoginCallback(
                {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                },
                {
                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
                }
            )
            val loginDto = LoginDto(email = username, password = password)
            APICaller.login(loginDto, loginCallback)
        }
        registerLButton.setOnClickListener {
            // Navigare către RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}