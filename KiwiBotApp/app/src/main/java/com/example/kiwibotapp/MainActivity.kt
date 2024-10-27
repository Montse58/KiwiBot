package com.example.kiwibotapp

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.kiwibotapp.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation setup
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Retrofit setup
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")  // Use 10.0.2.2 to connect to localhost from Android Emulator
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(BackendApi::class.java)

        // Test connection with the backend
        api.testConnection().enqueue(object : Callback<ConnectionResponse> {
            override fun onResponse(call: Call<ConnectionResponse>, response: Response<ConnectionResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message
                    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ConnectionResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed to connect to backend", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
