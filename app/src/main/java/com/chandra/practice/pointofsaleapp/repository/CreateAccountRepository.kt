package com.chandra.practice.pointofsaleapp.repository

import androidx.lifecycle.LiveData
import com.chandra.practice.pointofsaleapp.data.CreateAccountDetails
import com.chandra.practice.pointofsaleapp.roomdb.CreateAccountDao

class CreateAccountRepository(private val userDao: CreateAccountDao) {

    val loginUserDetails: LiveData<List<CreateAccountDetails>> = userDao.getAllUserDetails()

    suspend fun insert(user: CreateAccountDetails) {
        userDao.insert(user)
    }

    suspend fun update(user: CreateAccountDetails) {
        userDao.update(user)
    }

    suspend fun delete(user: CreateAccountDetails) {
        userDao.delete(user)
    }

    suspend fun getUserById(id: Long): CreateAccountDetails? {
        return userDao.getUserById(id)
    }
}
