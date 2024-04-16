package com.example.kotlincentralizer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlincentralizer.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    private var firstShow: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!firstShow) {
            Handler(Looper.getMainLooper()).postDelayed({
                redirectToLogin()
            }, 5000)
        } else redirectToLogin()
    }

    private fun redirectToLogin() {
        val logInInent = Intent(this, MainActivity::class.java)
        startActivity(logInInent)
        finish()
    }
}