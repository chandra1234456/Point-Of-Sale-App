package com.chandra.practice.pointofsaleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceFragmentCompat
import com.chandra.practice.pointofsaleapp.fragments.SettingsFragment
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener

class SettingsActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable Up Button (back navigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Optionally, use NavController to manage the navigation in the settings
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.settingsFragment) as NavHostFragment
         navController = navHostFragment.navController

        // Ensure the toolbar works with navigation (back navigation via the NavController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        // Load the Settings Fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
        }

        toolbar.setOnSingleClickListener {
            navController.navigate(R.id.profileFragment)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        // Handle Up navigation (back press)
        onBackPressed()
        return true
    }
}
