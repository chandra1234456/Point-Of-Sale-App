package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var loginBinding : FragmentLoginBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater)
        loginBinding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        loginBinding.tvCreateAcc.setOnClickListener {
            findNavController().navigate(R.id.createAccountFragment)
        }
        return loginBinding.root
    }
}