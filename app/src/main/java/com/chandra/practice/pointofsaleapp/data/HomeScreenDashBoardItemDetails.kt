package com.chandra.practice.pointofsaleapp.data


import com.google.gson.annotations.SerializedName

data class HomeScreenDashBoardItemDetails(
    @SerializedName("amount")
    var amount : String ,
    @SerializedName("imageTopIcon")
    var imageTopIcon : Int ,
    @SerializedName("imageTopIconName")
    var imageTopIconName : String ,
    @SerializedName("imageTopIconNameTextColor")
    var imageTopIconNameTextColor : Int ,


    )