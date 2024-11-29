package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chandra.practice.pointofsaleapp.databinding.FragmentAnalysisBinding
import com.chandra.practice.pointofsaleapp.repository.NewGenerateBillRepository
import com.chandra.practice.pointofsaleapp.roomdb.AppDatabase
import com.chandra.practice.pointofsaleapp.viewmodel.NewGenerateBillViewModel
import com.chandra.practice.pointofsaleapp.viewmodelfactory.NewGenerateBillViewModelFactory

class AnalysisFragment : Fragment() {
    private lateinit var analysisBinding : FragmentAnalysisBinding
    private lateinit var newGenerateBillViewModel : NewGenerateBillViewModel

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        analysisBinding = FragmentAnalysisBinding.inflate(layoutInflater)
        val userDao = AppDatabase.getDatabase(requireContext()).newGenerateBillDao()
        val repository = NewGenerateBillRepository(userDao)
        val viewModelFactory = NewGenerateBillViewModelFactory(repository)
        newGenerateBillViewModel = ViewModelProvider(this , viewModelFactory)[NewGenerateBillViewModel::class.java]
        newGenerateBillViewModel.allUsers.observe(viewLifecycleOwner) {
            Log.d("TAG" , "onCreateView: $it")
        }
        return analysisBinding.root
    }
}