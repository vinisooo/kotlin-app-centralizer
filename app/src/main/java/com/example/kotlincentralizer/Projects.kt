package com.example.kotlincentralizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlincentralizer.databinding.ActivityProjectsBinding

class Projects : AppCompatActivity() {
    private lateinit var binding: ActivityProjectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logOutBtn = binding.logOut.setOnClickListener {
            val logOutIntent = Intent(this, MainActivity::class.java)

            startActivity(logOutIntent)
        }
    }
}