package com.chandra.practice.pointofsaleapp.data


import java.math.BigDecimal

data class CustomerProductDetail(
    var comments: String,
    var productName: String,
    var productPrice: BigDecimal,
    var productQuantity: Int
)