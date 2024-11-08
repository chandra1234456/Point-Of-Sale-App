package com.chandra.practice.pointofsaleapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.LoginResult
import com.chandra.practice.pointofsaleapp.databinding.FragmentSplashScreenBinding
import com.chandra.practice.pointofsaleapp.util.EncryptionUtils
import com.chandra.practice.pointofsaleapp.util.retrieveTheCustomerValues
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class SplashScreenFragment : Fragment() {
    private lateinit var splashScreenBinding : FragmentSplashScreenBinding
    private lateinit var result : LoginResult

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        splashScreenBinding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return splashScreenBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        result = retrieveTheCustomerValues(requireContext())
        encryptExample()
        // Check if the device is rooted
        if(isDeviceRooted()){
           // Device is rooted, show a warning dialog or take action
            showRootWarningDialog()
        }else {
            // Launch coroutine for the delay
            lifecycleScope.launch {
                delay(3000) // Delay for 3 seconds
                navigateToLoginFragment()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun encryptExample() {
        // Example usage
        val secretKey = EncryptionUtils.generateSecretKey()
        val plainText = "This is a secret message"

        // Encrypt
        val encrypted = EncryptionUtils.encrypt(plainText, secretKey)
        println("Encrypted Text: $encrypted")

        // Decrypt
        val decrypted = EncryptionUtils.decrypt(encrypted, secretKey)
        println("Decrypted Text: $decrypted")
    }

    private fun isDeviceRooted(): Boolean {
        return (hasSuperuserBinary() || hasTestKeys() || canWriteToSystem())
    }
    private fun hasSuperuserBinary(): Boolean {
        val paths = arrayOf("/system/xbin/su", "/system/bin/su", "/system/su")
        for (path in paths) {
            if (File(path).exists()) {
                return true
            }
        }
        return false
    }
    private fun hasTestKeys(): Boolean {
        return Build.TAGS?.contains("test-keys") == true
    }
    private fun showRootWarningDialog() {
        AlertDialog.Builder(requireContext())
                .setTitle("Warning")
                .setMessage("Your device is rooted, which may cause security issues.")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
    }

    private fun canWriteToSystem(): Boolean {
        val testFile = File("/system/app/Superuser.apk")
        return testFile.exists() && testFile.canWrite()
    }


    private fun navigateToLoginFragment() {
        if (result.loginCheck) {
            findNavController().navigate(
                    R.id.homeFragment ,
                    null ,
                    NavOptions.Builder()
                            .setPopUpTo(
                                    R.id.splashScreenFragment ,
                                    true
                                       ) // Remove SplashScreenFragment from back stack
                        .build()
                                        )
        } else {
            findNavController().navigate(
                    R.id.loginFragment ,
                    null ,
                    NavOptions.Builder()
                            .setPopUpTo(
                                    R.id.splashScreenFragment ,
                                    true
                                       ) // Remove SplashScreenFragment from back stack
                            .build()
                                        )
        }
    }
}