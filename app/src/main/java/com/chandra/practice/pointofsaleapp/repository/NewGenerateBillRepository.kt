package com.chandra.practice.pointofsaleapp.repository

import androidx.lifecycle.LiveData
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.roomdb.NewGenerateBillDao

class NewGenerateBillRepository(private val userDao: NewGenerateBillDao) {

    val allUsers: LiveData<List<NewGenerateBillCustomerDetails>> = userDao.getAllUsers()

    suspend fun insert(user: NewGenerateBillCustomerDetails) {
        userDao.insert(user)
    }

    suspend fun update(user: NewGenerateBillCustomerDetails) {
        userDao.update(user)
    }

    suspend fun delete(user: NewGenerateBillCustomerDetails) {
        userDao.delete(user)
    }

    suspend fun getUserById(id: Long): NewGenerateBillCustomerDetails? {
        return userDao.getUserById(id)
    }
}
