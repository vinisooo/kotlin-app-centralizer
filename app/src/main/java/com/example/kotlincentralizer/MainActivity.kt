package com.example.kotlincentralizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlincentralizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signinBtn.setOnClickListener {
            // the most secure login you've  ever seen
            val nickName = binding.nickInput.text.toString()
            val pwd = binding.pwdInput.text.toString()

            println("$nickName, $pwd")

            val isNickNameRight: Boolean = nickName.equals("vinisooo")
            val isPasswordRight: Boolean = pwd.equals("123456")

            if(isNickNameRight && isPasswordRight) {
                Toast.makeText(this, "Welcome, Vinícius!", Toast.LENGTH_LONG).show()

                val logInNavigate = Intent(this, Projects::class.java)
                startActivity(logInNavigate)
            } else {
                Toast.makeText(this, "Wrong nickname or password. Try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}