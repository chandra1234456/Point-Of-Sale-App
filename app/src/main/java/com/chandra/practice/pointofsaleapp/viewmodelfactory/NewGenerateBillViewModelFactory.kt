package com.chandra.practice.pointofsaleapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chandra.practice.pointofsaleapp.repository.NewGenerateBillRepository
import com.chandra.practice.pointofsaleapp.viewmodel.NewGenerateBillViewModel

class NewGenerateBillViewModelFactory(private val repository : NewGenerateBillRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(NewGenerateBillViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewGenerateBillViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}