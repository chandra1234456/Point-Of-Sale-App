package com.chandra.practice.pointofsaleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.repository.NewGenerateBillRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewGenerateBillViewModel(private val repository: NewGenerateBillRepository) : ViewModel() {

    val allUsers: LiveData<List<NewGenerateBillCustomerDetails>> = repository.allUsers

    fun insert(user: NewGenerateBillCustomerDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun update(user: NewGenerateBillCustomerDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    fun delete(user: NewGenerateBillCustomerDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }

    fun getUserById(id: Long): LiveData<NewGenerateBillCustomerDetails?> {
        val result = MutableLiveData<NewGenerateBillCustomerDetails?>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repository.getUserById(id))
        }
        return result
    }
}
