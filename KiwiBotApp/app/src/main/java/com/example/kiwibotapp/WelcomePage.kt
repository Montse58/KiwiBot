package com.example.kiwibotapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        // Ensure the 'main' view exists before using it
        val mainView = findViewById<View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } else {
            Log.e("WelcomePage", "'main' view not found in activity_welcome_page.xml")
        }

        // Initialize Retrofit and make API call to backend
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")  // Use 10.0.2.2 for Emulator, or your local IP for a physical device
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(BackendApi::class.java)

        // Make the API call to the Flask backend
        api.testConnection().enqueue(object : Callback<ConnectionResponse> {
            override fun onResponse(call: Call<ConnectionResponse>, response: Response<ConnectionResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message
                    Toast.makeText(applicationContext, "Backend says: $message", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ConnectionResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed to connect to backend: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
