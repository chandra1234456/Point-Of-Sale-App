package com.chandra.practice.pointofsaleapp.data


import com.google.gson.annotations.SerializedName

data class AddProductItemDetails(
    @SerializedName("comments")
    var comments: String,
    @SerializedName("productName")
    var productName: String,
    @SerializedName("productPrice")
    var productPrice: String,
    @SerializedName("quantity")
    var quantity: String
)