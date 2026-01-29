package com.example.e_banking
//LoginActivity.kt
import android.content.Intent
import android.os.Bundle
import android.util.Log//sterge dupa test
import android.widget.Button
import android.widget.EditText
import android.widget.Toast//sterge dupa test
import androidx.appcompat.app.AppCompatActivity

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
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

//////////////////////chestii de test login
            Log.i("Login", "Username: $username, Password: $password")
            //TODO: Login logic

            val correctUsername = "admin"
            val correctPassword = "1234"

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completează toate câmpurile", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (username == correctUsername && password == correctPassword) {
                Log.i("LoginDebug", "Autentificare reușită ✅")
                Toast.makeText(this, "Login cu succes!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            else {
                Log.i("LoginDebug", "Autentificare eșuată ❌")
                Toast.makeText(this, "Username sau parola incorecte", Toast.LENGTH_SHORT).show()
            }

        }
        registerLButton.setOnClickListener {
            // Navigare către RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}