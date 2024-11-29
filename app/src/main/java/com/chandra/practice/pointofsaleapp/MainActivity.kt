package com.chandra.practice.pointofsaleapp

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.chandra.practice.pointofsaleapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private  var mainBinding : ActivityMainBinding? =null
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding !!.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        NavigationUI.setupWithNavController(bottomNavigationView , navController)
        //Handle bottom nav visibility
        navController.addOnDestinationChangedListener { _ , destination , _ ->
            when (destination.id) {
                R.id.homeFragment , R.id.profileFragment , R.id.creditBillFragment , R.id.notificationFragment , R.id.analysisFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }

                else -> {
                    bottomNavigationView.visibility = View.GONE

                }

            }
        }
        // Handle navigation item selections
        mainBinding !!.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.analysis -> {
                    navController.navigate(R.id.analysisFragment)
                    true
                }

                R.id.createBill -> {
                    navController.navigate(R.id.creditBillFragment)
                    true
                }

                R.id.notification -> {
                    navController.navigate(R.id.notificationFragment)
                    true
                }

                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }
        setupBackPressedHandler()

    }
    private fun setupBackPressedHandler() {
        // Handling the back press when bottom navigation is used
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Check if the NavController can pop back stack
                if (navController.currentDestination?.id == R.id.homeFragment) {
                    // If at the start fragment (firstFragment), allow back press
                    finish()  // Or implement custom exit logic
                } else {
                    // Otherwise, just pop the back stack
                    navController.popBackStack()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
    // Handle ActionBar's back button (optional)
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }
}