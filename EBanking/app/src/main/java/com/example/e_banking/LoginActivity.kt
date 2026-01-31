package com.example.e_banking
//LoginActivity.kt
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_banking.api.APIProvider
import com.example.e_banking.api.SecurityContext
import com.example.e_banking.api.callbacks.DefaultCallback
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

        usernameInput = findViewById(R.id.usernamel_input)
        passwordInput = findViewById(R.id.passwordl_input)
        loginButton = findViewById(R.id.login_button)
        registerLButton = findViewById(R.id.registerl_button)

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
                    Log.i("LoginDebug", "Autentificare reușită ✅")
                    Toast.makeText(this, "Login cu succes!", Toast.LENGTH_SHORT).show()
                    APIProvider.api.test(SecurityContext.authHeaderValue)
                        .enqueue(DefaultCallback())
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                },
                {
                    Log.i("LoginDebug", "Autentificare eșuată ❌")
                    Toast.makeText(this, "Username sau parola incorecte", Toast.LENGTH_SHORT).show()
                }
            )
            val loginDto = LoginDto(email = username, password = password)
            APIProvider.api.login(loginDto).enqueue(loginCallback)
        }
        registerLButton.setOnClickListener {
            // Navigare către RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}