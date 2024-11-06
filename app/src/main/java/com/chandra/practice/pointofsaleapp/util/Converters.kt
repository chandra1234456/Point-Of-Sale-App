package com.chandra.practice.pointofsaleapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.chandra.practice.pointofsaleapp.data.CustomerProductDetail

class Converters {

    // Convert List<CustomerProductDetail> to String (JSON)
    @TypeConverter
    fun fromCustomerProductDetailList(value: List<CustomerProductDetail>?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Converts the list to a JSON string
    }

    // Convert String (JSON) back to List<CustomerProductDetail>
    @TypeConverter
    fun toCustomerProductDetailList(value: String?): List<CustomerProductDetail>? {
        val gson = Gson()
        val listType = object : TypeToken<List<CustomerProductDetail>>() {}.type
        return gson.fromJson(value, listType)  // Converts the JSON string back to a list
    }
}
