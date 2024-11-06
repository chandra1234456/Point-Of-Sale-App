package com.chandra.practice.pointofsaleapp.util

import android.content.Context
import android.view.LayoutInflater
import com.chandra.practice.pointofsaleapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textview.MaterialTextView

class CustomBottomSheet(context : Context) {

    private val bottomSheetDialog = BottomSheetDialog(context, R.style.MyBottomSheetDialog)
    // Interface for handling button clicks with extra info
    interface BottomSheetClickListener {
        fun onChooseClick(selectedText: String)
    }
    // Function to show the Bottom Sheet with dynamic content
    fun showBottomSheet(title: String, message: String,context : Context,listener : BottomSheetClickListener) {
        // Inflate the custom layout for the bottom sheet
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet, null)

        // Get references to the views and set the title and message
        val titleTextView: MaterialTextView = bottomSheetView.findViewById(R.id.captureImage)
        val messageTextView: MaterialTextView = bottomSheetView.findViewById(R.id.uploadImage)

        titleTextView.text = title
        messageTextView.text = message

        titleTextView.setOnSingleClickListener {
            listener.onChooseClick(titleTextView.text.toString())
            bottomSheetDialog.dismiss()
        }
        messageTextView.setOnSingleClickListener {
            listener.onChooseClick(messageTextView.text.toString())
            bottomSheetDialog.dismiss()
        }


        // Set the content view for the BottomSheetDialog
        bottomSheetDialog.setContentView(bottomSheetView)

        // Show the Bottom Sheet
        bottomSheetDialog.show()
    }

}
