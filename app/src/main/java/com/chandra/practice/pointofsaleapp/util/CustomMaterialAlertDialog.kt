package com.chandra.practice.pointofsaleapp.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.chandra.practice.pointofsaleapp.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class CustomMaterialAlertDialog(
    private val context: Context,
    private val title: String = "",
    private val message: String = "",
    private val hint: String = "",
    private val positiveButtonAction: (String) -> Unit = {},
    private val negativeButtonAction: () -> Unit = {}
) {

    private lateinit var inputEditText: TextInputEditText
    private lateinit var inputLayout: TextInputLayout
    private lateinit var dialog: AlertDialog

    // Method to show the dialog
    fun show() {
        // Inflate the custom layout
        val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialog_custom_material, null)

        // Find views
        inputEditText = dialogView.findViewById(R.id.inputEditText)
        inputLayout = dialogView.findViewById(R.id.inputLayout)

        // Set title and message (if provided)
        val titleTextView: MaterialTextView = dialogView.findViewById(R.id.dialogTitle)
        val messageTextView: MaterialTextView = dialogView.findViewById(R.id.dialogMessage)
        val btnUpdate: MaterialButton = dialogView.findViewById(R.id.btnUpdate)
        val btnCancel: MaterialButton = dialogView.findViewById(R.id.btnCancel)

        titleTextView.text = title
        messageTextView.text = message

        // Set the hint for the input field
        inputLayout.hint = hint

        // Create the custom AlertDialog
        dialog = MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnCancel.setOnSingleClickListener {
            negativeButtonAction.invoke()
            dialog.dismiss()
        }
        btnUpdate.setOnSingleClickListener {
            positiveButtonAction.invoke(inputEditText.text.toString())
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }

    // Handle the positive button action
    private fun handlePositiveButtonAction() {
        val inputText = inputEditText.text.toString()
        if (inputText.isNotEmpty()) {
            positiveButtonAction(inputText)
            dialog.dismiss()
        } else {
            inputLayout.error = "Please enter a value" // Show error if no input
        }
    }

    // Handle the negative button action
    private fun handleNegativeButtonAction() {
        negativeButtonAction()
        dialog.dismiss()
    }
}
