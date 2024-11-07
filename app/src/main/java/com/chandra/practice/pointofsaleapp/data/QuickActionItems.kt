package com.chandra.practice.pointofsaleapp.data


import com.google.gson.annotations.SerializedName

data class QuickActionItems(
    @SerializedName("transactionImageRes")
    var transactionImageRes : Int ,
    @SerializedName("transactionText")
    var transactionText : String ,
    @SerializedName("transactionBackgroundColor")
    var transactionBackgroundColor : Int ,
    @SerializedName("transactionTextColor")
    var transactionTextColor : Int ,

    )