package com.chandra.practice.pointofsaleapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chandra.practice.pointofsaleapp.data.CreateAccountDetails

@Dao
interface CreateAccountDao {

    @Insert
    suspend fun insert(user: CreateAccountDetails)

    @Update
    suspend fun update(user: CreateAccountDetails)

    @Delete
    suspend fun delete(user: CreateAccountDetails)

    @Query("SELECT * FROM userCreationTable ORDER BY customerFullName ASC")
    fun getAllUsers(): LiveData<List<CreateAccountDetails>>

    @Query("SELECT * FROM userCreationTable WHERE id = :id")
    suspend fun getUserById(id: Long): CreateAccountDetails?
}
