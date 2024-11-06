package com.chandra.practice.pointofsaleapp.viewmodel

import androidx.lifecycle.*
import com.chandra.practice.pointofsaleapp.data.CreateAccountDetails
import com.chandra.practice.pointofsaleapp.repository.CreateAccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val repository: CreateAccountRepository) : ViewModel() {

    val allUsers: LiveData<List<CreateAccountDetails>> = repository.allUsers

    fun insert(user: CreateAccountDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun update(user: CreateAccountDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    fun delete(user: CreateAccountDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }

    fun getUserById(id: Long): LiveData<CreateAccountDetails?> {
        val result = MutableLiveData<CreateAccountDetails?>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repository.getUserById(id))
        }
        return result
    }
}
