package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {
    private lateinit var createAccountBinding : FragmentCreateAccountBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        createAccountBinding = FragmentCreateAccountBinding.inflate(layoutInflater)
        createAccountBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        return createAccountBinding.root
    }
}