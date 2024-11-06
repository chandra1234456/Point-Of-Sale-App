package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.BuildCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.adapter.ProfileItemsAdapter
import com.chandra.practice.pointofsaleapp.data.ProfileItemsDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(),ProfileItemsAdapter.OnProfileItemsClickListners {
    private lateinit var profileBinding : FragmentProfileBinding
    private lateinit var profileItemsAdapter : ProfileItemsAdapter
    private var profileList : MutableList<ProfileItemsDetails> = mutableListOf()
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)
        profileList = listOf(
                ProfileItemsDetails("Help & Support", R.drawable.ic_help , R.drawable.ic_right_icon),
                ProfileItemsDetails("About App", R.drawable.ic_about_app , R.drawable.ic_right_icon),
                ProfileItemsDetails("Logout", R.drawable.ic_help , R.drawable.ic_right_icon),
                ProfileItemsDetails("Settings", R.drawable.ic_about_app , R.drawable.ic_right_icon),
                            ).toMutableList()
        profileItemsAdapter = ProfileItemsAdapter(this,profileList)
        profileBinding.profileItemsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        profileBinding.profileItemsRecyclerview.adapter = profileItemsAdapter
        profileBinding.ivBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
      //  profileBinding.tvAppVersion.text = "${BuildConfig.VERSION_NAME} V"
        return profileBinding.root
    }

    override fun onClickEndIconRes(position : Int , profileItemsDetails : ProfileItemsDetails) {
        Toast.makeText(requireContext() , "$position" , Toast.LENGTH_SHORT).show()
    }

}