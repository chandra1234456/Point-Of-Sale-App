package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chandra.practice.pointofsaleapp.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var notificationBinding : FragmentNotificationBinding
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        notificationBinding = FragmentNotificationBinding.inflate(layoutInflater)
        return notificationBinding.root
    }
}