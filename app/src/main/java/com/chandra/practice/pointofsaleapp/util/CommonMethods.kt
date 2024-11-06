package com.chandra.practice.pointofsaleapp.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.View
import com.chandra.practice.pointofsaleapp.data.LoginResult
import com.chandra.practice.pointofsaleapp.util.Constants.CUSTOMER_CREATE_ACCOUNT
import com.chandra.practice.pointofsaleapp.util.Constants.EMAIL
import com.chandra.practice.pointofsaleapp.util.Constants.LOGIN_CHECK
import com.chandra.practice.pointofsaleapp.util.Constants.PASSWORD
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date


fun getTodayDate() : String {
    val sdf = SimpleDateFormat("dd-MMM-yyyy")
    val currentDate = sdf.format(Date())
    return currentDate
}

fun storeCustomerValues(
    context : Context ,
    email : String ,
    password : String ,
    isLogin : Boolean ,
                       ) {
    val sharedPreferences = context.getSharedPreferences(CUSTOMER_CREATE_ACCOUNT , MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(EMAIL , email)
    editor.putString(PASSWORD , password)
    editor.putBoolean(LOGIN_CHECK , isLogin)
    editor.apply()
}

fun retrieveTheCustomerValues(context : Context) : LoginResult {
    val prefs = context.getSharedPreferences(CUSTOMER_CREATE_ACCOUNT , MODE_PRIVATE)
    val email = prefs.getString(EMAIL , "empty")
    val password = prefs.getString(PASSWORD , "empty")
    val loginCheck = prefs.getBoolean(LOGIN_CHECK , false)
    return LoginResult(email , password , loginCheck)
}


fun showErrorSnackBar(view : View , message : String) {
    val snackBar = Snackbar.make(view , message , Snackbar.LENGTH_SHORT).show()
  //  snackBar.view.setBackgroundColor(backgroundColor)
            //  snackBar.show()
}

fun showCustomActionSnackBar(
    view : View ,
    message : String ,
    actionText : String ,
    actionListener : View.OnClickListener ,
                            ) {
    Snackbar.make(view , message , Snackbar.LENGTH_LONG)
            .setAction(actionText , actionListener)
            .show()
}