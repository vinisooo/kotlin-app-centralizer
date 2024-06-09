package com.example.kotlincentralizer.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlincentralizer.databinding.ActivityDogGeneratorBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class DogGenerator : AppCompatActivity() {
    private lateinit var binding: ActivityDogGeneratorBinding
    private var client = OkHttpClient()
    private val url = "https://dog.ceo/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogGeneratorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateBtn.setOnClickListener { generateDog() }
    }

    private fun generateDog() {
        val endpoint = "breeds/image/random"
        val request = Request.Builder().url(url + endpoint).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    binding.jsonResponse.text = "Something went wrong: ${e.message}"
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    runOnUiThread {
                        binding.jsonResponse.text = "Unexpected code $response"
                    }
                    return
                }

                val responseData = response.body?.string()
                if (responseData != null) {
                    val jsonObject = JSONObject(responseData)
                    val imageUrl = jsonObject.getString("message")
                    runOnUiThread {
                        binding.jsonResponse.text = imageUrl

                        Glide.with(this@DogGenerator).load(imageUrl).into(binding.dogImage)
                    }
                } else {
                    runOnUiThread {
                        binding.jsonResponse.text = "Response was null"
                    }
                }
            }
        })
    }
}
