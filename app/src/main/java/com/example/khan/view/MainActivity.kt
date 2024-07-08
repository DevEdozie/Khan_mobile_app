package com.example.khan.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.khan.R
import com.example.khan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declare the binding object for this activity
    private lateinit var binding: ActivityMainBinding

    // Initialize the activity and set up the navigation controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this activity
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set up NavController to manage navigation within the app
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // Set the content view to the inflated layout
        setContentView(binding.root)
    }
}
