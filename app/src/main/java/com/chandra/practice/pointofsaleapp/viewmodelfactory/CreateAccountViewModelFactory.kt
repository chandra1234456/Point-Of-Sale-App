package com.chandra.practice.pointofsaleapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chandra.practice.pointofsaleapp.repository.CreateAccountRepository
import com.chandra.practice.pointofsaleapp.viewmodel.CreateAccountViewModel

class CreateAccountViewModelFactory(private val repository: CreateAccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateAccountViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}