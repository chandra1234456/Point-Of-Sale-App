package com.chandra.practice.pointofsaleapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userCreationTable")
data class CreateAccountDetails(
    @PrimaryKey(autoGenerate = true) val id : Int = 0 ,
    @SerializedName("billRules")
    var billRules : String ,
    @SerializedName("businessAddress")
    var businessAddress : String ,
    @SerializedName("businessName")
    var businessName : String ,
    @SerializedName("customerSignaturePhoto")
    var customerSignaturePhoto : String ,
    @SerializedName("customerFullName")
    var customerFullName : String ,
    @SerializedName("emailAddress")
    var emailAddress : String ,
    @SerializedName("gSTNumber")
    var gSTNumber : String ,
    @SerializedName("noteInfo")
    var noteInfo : String ,
    @SerializedName("password")
    var password : String ,
    @SerializedName("phoneNumber")
    var phoneNumber : String ,
                               )