package com.chandra.practice.pointofsaleapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails

@Dao
interface NewGenerateBillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // REPLACE will update the existing row with the same 'id'
    suspend fun insert(user : NewGenerateBillCustomerDetails)

    @Update
    suspend fun update(user : NewGenerateBillCustomerDetails)

    @Delete
    suspend fun delete(user : NewGenerateBillCustomerDetails)

    @Query("SELECT * FROM userGenerateBillTable ORDER BY customerName ASC")
    fun getAllUsers() : LiveData<List<NewGenerateBillCustomerDetails>>

    @Query("SELECT * FROM userGenerateBillTable WHERE id = :id")
    suspend fun getUserById(id : Long) : NewGenerateBillCustomerDetails?
}