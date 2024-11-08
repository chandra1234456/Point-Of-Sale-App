package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chandra.practice.pointofsaleapp.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private lateinit var editProfileBinding : FragmentEditProfileBinding

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        editProfileBinding = FragmentEditProfileBinding.inflate(layoutInflater)
        return editProfileBinding.root
    }
}