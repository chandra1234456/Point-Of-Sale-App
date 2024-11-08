package com.chandra.practice.pointofsaleapp.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.AboutAppItemDetails
import com.chandra.practice.pointofsaleapp.data.LoginResult
import com.chandra.practice.pointofsaleapp.util.Constants.CUSTOMER_CREATE_ACCOUNT
import com.chandra.practice.pointofsaleapp.util.Constants.EMAIL
import com.chandra.practice.pointofsaleapp.util.Constants.LOGIN_CHECK
import com.chandra.practice.pointofsaleapp.util.Constants.PASSWORD
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
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


// Function to create a custom AlertDialog with a title, message, and two buttons
fun showCustomAlertDialog(
    context: Context,
    title: String,
    message: String,
    positiveButtonText: String = "OK",
    negativeButtonText: String = "Cancel",
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit = {}
                         ) {
    var dialog :AlertDialog ?= null
    // Create a custom view for the dialog
    val dialogView: View = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null)

    // Find the TextViews for title and message
    val tvTitle: MaterialTextView = dialogView.findViewById(R.id.tvTitle)
    val tvMessage: MaterialTextView = dialogView.findViewById(R.id.tvMessage)
    val btnOk: MaterialButton = dialogView.findViewById(R.id.btnOk)
    val btnCancel: MaterialButton = dialogView.findViewById(R.id.btnCancel)

    // Set the title and message to the TextViews
    tvTitle.text = title
    tvMessage.text = message

    // Build the dialog
      dialog = MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setCancelable(false) // Allow dismissing the dialog by tapping outside
            .setPositiveButton(positiveButtonText) { _, _ ->
                onPositiveClick() // Trigger the positive button click action
                dialog?.dismiss()
            }
            .setNegativeButton(negativeButtonText) { _, _ ->
                dialog?.dismiss()
               // onNegativeClick() // Trigger the negative button click action
            }
            .create()

    // Show the dialog
    dialog.show()
}

fun createFeatureList(): MutableList<AboutAppItemDetails> {
    val featureList = mutableListOf<AboutAppItemDetails>()

    // key features
    featureList.add(AboutAppItemDetails("Key Features", isHeader = true))
    featureList.add(AboutAppItemDetails("Real-time Transaction Management : ", "The app allows you to easily record each transaction, whether it's a sale, purchase, refund, or exchange, providing you with up-to-date insights into your business performance."))
    featureList.add(AboutAppItemDetails("Customer Management:","Keep track of all your customers, including contact details, transaction history, and payment methods. Build a loyal customer base by providing personalized service and communication."))
    featureList.add(AboutAppItemDetails("Inventory Tracking: ","Easily manage stock levels and keep tabs on your inventory. The app notifies you when products are low in stock, allowing you to reorder in time and avoid stockouts that could affect your sales."))
    featureList.add(AboutAppItemDetails("Payment Integration:"," The app supports multiple payment methods, whether cash, credit cards, or digital payments, to ensure seamless transactions. You can also keep track of outstanding amounts and dues to maintain smooth cash flow."))
    featureList.add(AboutAppItemDetails("Invoicing & Billing:" ,"Create professional invoices and receipts for customers, with all necessary details like the transaction date, products sold, taxes, discounts, and total amount. You can send invoices directly to your customers via email or save them for future reference."))
    featureList.add(AboutAppItemDetails("Security & Privacy: ","Your business data is protected with robust encryption and security measures, ensuring that sensitive information about your transactions, customers, and financials is kept safe. The app provides user-level access controls so that only authorized personnel can access confidential business information."))
    featureList.add(AboutAppItemDetails("User-friendly Interface: " ,"The intuitive and easy-to-navigate interface ensures that even users with minimal technical knowledge can manage their business operations effortlessly. Whether you're on your computer, tablet, or smartphone, the app adapts to your device for a seamless experience."))
    featureList.add(AboutAppItemDetails("Detailed Reports & Analytics", "Understand your business performance with in-depth sales analytics and reports. The app provides daily, weekly, and monthly statistics, helping you track sales trends, revenue, and growth."))

    //Why Choose This App for Your Business?
    featureList.add(AboutAppItemDetails("Why Choose This App for Your Business?", isHeader = true))
    featureList.add(AboutAppItemDetails("Boost Efficiency", "Save time with automated tracking of sales, inventory, and payments, allowing you to focus on running your business instead of manually managing records."))
    featureList.add(AboutAppItemDetails("Make Informed Decisions: ","The app’s reporting and analytics features give you a comprehensive view of your business performance, helping you make better financial and operational decisions."))
    featureList.add(AboutAppItemDetails("Access Anywhere, Anytime:","With cloud-based access, you can manage your business from anywhere, whether you're at the shop, at home, or on the go."))
    featureList.add(AboutAppItemDetails("Grow Your Business:","By keeping accurate records and gaining insights into customer preferences and buying habits, you can improve your marketing efforts and expand your customer base."))
    featureList.add(AboutAppItemDetails("Easy Integration:","The app integrates smoothly with existing business processes, including accounting software, POS systems, and online payment platforms, making it a versatile addition to your business workflow."))
    //Security
    featureList.add(AboutAppItemDetails("App Security and Data Privacy", isHeader = true))
    featureList.add(AboutAppItemDetails("Security & Privacy", "Your business data is protected with robust encryption and security measures, ensuring that sensitive information about your transactions, customers, and financials is kept safe."))
    featureList.add(AboutAppItemDetails("Perfect for Every Type of Business", isHeader = true))
    featureList.add(AboutAppItemDetails("Retail Stores", "Track your sales, manage your products, and generate reports to understand which products are performing best."))

    return featureList
}

