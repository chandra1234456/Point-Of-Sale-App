package com.chandra.practice.pointofsaleapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.chandra.practice.pointofsaleapp.util.Converters

@Entity(tableName = "userGenerateBillTable")
data class NewGenerateBillCustomerDetails(
    @PrimaryKey(autoGenerate = true) var id : Long = 0 ,
    var customerName : String ,
    @TypeConverters(Converters::class)
    var customerProductDetails : List<CustomerProductDetail> ,
    var paidAmount : String ,
    var paymentMethod : String ,
    var paymentStatus : String ,
    var phoneNumber : String ,
    var fullyNotPaid : Boolean ,
    var totalAmount : String ,
    var remainingAmount : String ,
    var transactionDateTime : String ,
                                         )