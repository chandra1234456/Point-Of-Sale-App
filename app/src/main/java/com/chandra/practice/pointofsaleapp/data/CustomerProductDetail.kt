package com.chandra.practice.pointofsaleapp.data


import androidx.room.TypeConverters
import com.chandra.practice.pointofsaleapp.util.Converters
import com.google.gson.annotations.SerializedName
@TypeConverters(Converters::class)  // Register the converters at the entity level as well
data class CustomerProductDetail(
    @SerializedName("comments")
    var comments: String,
    @SerializedName("productName")
    var productName: String,
    @SerializedName("productPrice")
    var productPrice: String,
    @SerializedName("productQuantity")
    var productQuantity: Int
)