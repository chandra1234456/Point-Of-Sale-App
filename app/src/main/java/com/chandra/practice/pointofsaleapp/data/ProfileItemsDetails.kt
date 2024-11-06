package com.chandra.practice.pointofsaleapp.data


import com.google.gson.annotations.SerializedName

data class ProfileItemsDetails(
    @SerializedName("ImageIconName")
    var imageIconName : String ,
    @SerializedName("imageStartIconRes")
    var imageStartIconRes : Int ,
    @SerializedName("imageEndIconRes")
    var imageEndIconRes : Int ,
                              )