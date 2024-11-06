package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.LoginResult
import com.chandra.practice.pointofsaleapp.databinding.FragmentSplashScreenBinding
import com.chandra.practice.pointofsaleapp.util.retrieveTheCustomerValues
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        result = retrieveTheCustomerValues(requireContext())

        // Launch coroutine for the delay
        lifecycleScope.launch {
            delay(3000) // Delay for 3 seconds
            navigateToLoginFragment()
        }
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