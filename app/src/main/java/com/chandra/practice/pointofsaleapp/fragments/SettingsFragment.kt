package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.preference.ListPreference
import androidx.preference.EditTextPreference
import androidx.preference.CheckBoxPreference
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.chandra.practice.pointofsaleapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Load preferences from XML
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        // Example of handling preference changes manually
        val darkModePreference = findPreference<SwitchPreferenceCompat>("dark_mode")
        darkModePreference?.setOnPreferenceChangeListener { preference, newValue ->
            val isEnabled = newValue as Boolean
            // Handle dark mode toggle here (apply theme, etc.)
            true // Return true to save the change
        }

        val languagePreference = findPreference<ListPreference>("language")
        languagePreference?.setOnPreferenceChangeListener { preference, newValue ->
            val language = newValue as String
            // Handle language change here
            true // Return true to save the change
        }

        val usernamePreference = findPreference<EditTextPreference>("username")
        usernamePreference?.setOnPreferenceChangeListener { preference, newValue ->
            val username = newValue as String
            // Save username, validate if necessary
            true // Return true to save the change
        }

        val notificationsEnabledPreference = findPreference<CheckBoxPreference>("notifications_enabled")
        notificationsEnabledPreference?.setOnPreferenceChangeListener { preference, newValue ->
            val isEnabled = newValue as Boolean
            // Handle enabling/disabling notifications here
            true // Return true to save the change
        }
    }

    private fun savePreference(key: String, value: Any) {
        val sharedPreferences: SharedPreferences? =
            context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val editor = sharedPreferences?.edit()
        when (value) {
            is Boolean -> editor?.putBoolean(key, value)
            is String -> editor?.putString(key, value)
            is Int -> editor?.putInt(key, value)
            else -> throw IllegalArgumentException("Unsupported preference type")
        }
        editor?.apply()
    }
}
