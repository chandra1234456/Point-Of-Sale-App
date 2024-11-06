package com.chandra.practice.pointofsaleapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userGenerateBillTable")
data class NewGenerateBillCustomerDetails(
    @PrimaryKey(autoGenerate = true) val id : Int = 0 ,
    @SerializedName("customerName")
    var customerName : String ,
    @SerializedName("customerProductDetails")
    var customerProductDetails : List<CustomerProductDetail> ,
    @SerializedName("paidAmount")
    var paidAmount : String ,
    @SerializedName("paymentMethod")
    var paymentMethod : String ,
    @SerializedName("paymentStatus")
    var paymentStatus : String ,
    @SerializedName("phoneNumber")
    var phoneNumber : String ,
    @SerializedName("fullyPaidOrNot")
    var fullyPaidOrNot : Boolean ,
                                         )