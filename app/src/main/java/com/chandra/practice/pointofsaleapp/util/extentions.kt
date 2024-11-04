package com.chandra.practice.pointofsaleapp.util

import android.content.Context
import android.view.View
import android.widget.Toast

private const val CLICK_DELAY = 500L // Milliseconds

fun View.setOnSingleClickListener(onClick: (View) -> Unit) {
    var lastClickTime = 0L

    this.setOnClickListener { view ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > CLICK_DELAY) {
            lastClickTime = currentTime
            onClick(view)
        }
    }
}

fun Context.toastMsg(message :String ,context : Context){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}