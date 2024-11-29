package com.chandra.practice.pointofsaleapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.chandra.practice.pointofsaleapp.data.CustomerProductDetail

class Converters {
    // Convert List<CustomerProductDetail> to JSON string
    @TypeConverter
    fun fromCustomerProductDetailList(value: List<CustomerProductDetail>?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Converts list to JSON string
    }

    // Convert JSON string back to List<CustomerProductDetail>
    @TypeConverter
    fun toCustomerProductDetailList(value: String?): List<CustomerProductDetail>? {
        val gson = Gson()
        val listType = object : TypeToken<List<CustomerProductDetail>>() {}.type
        return gson.fromJson(value, listType)  // Converts JSON string back to list
    }
}
