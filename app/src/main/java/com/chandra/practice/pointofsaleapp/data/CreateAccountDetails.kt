package com.chandra.practice.pointofsaleapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userCreationTable")
data class CreateAccountDetails(
    @PrimaryKey(autoGenerate = true) val id : Int = 0 ,
    var billRules : String ,
    var businessAddress : String ,
    var businessName : String ,
    var customerSignaturePhoto : String ,
    var customerFullName : String ,
    var emailAddress : String ,
    var gSTNumber : String ,
    var noteInfo : String ,
    var password : String ,
    var phoneNumber : String ,
                               )