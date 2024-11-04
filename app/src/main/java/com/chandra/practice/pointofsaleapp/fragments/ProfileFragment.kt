package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var profileBinding : FragmentProfileBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)
        profileBinding.ivBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        return profileBinding.root
    }

}