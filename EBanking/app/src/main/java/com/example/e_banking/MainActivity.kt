package com.example.e_banking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initializare Views
        bottomNavigation = findViewById(R.id.bottom_navigation)


        // Setează fragmentul inițial
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment()).commit()

        // BottomNavigationView listener
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                    true
                }
                R.id.payment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, PaymentFragment())
                        .commit()
                    true
                }
                R.id.account -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, AdminFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
