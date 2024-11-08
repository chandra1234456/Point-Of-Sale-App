package com.chandra.practice.pointofsaleapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.databinding.FragmentContactUsBinding


class ContactUsFragment : Fragment() {
  private lateinit var contactUsBinding : FragmentContactUsBinding

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        contactUsBinding = FragmentContactUsBinding.inflate(layoutInflater)
        contactUsBinding.sendEmailButton.setOnClickListener {
           val issueText = contactUsBinding.issueText.text.toString()
            sendEmail(issueText)
        }
        return contactUsBinding.root
    }

    private fun sendEmail(issueText:String) {
        // Create the email intent using ACTION_SEND (instead of ACTION_SENDTO)
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            // Add the email recipient, subject, and body
            putExtra(Intent.EXTRA_EMAIL, arrayOf("chandrachandra98765@gmail.com")) // email recipients
            putExtra(Intent.EXTRA_SUBJECT, "Support Request")  // subject
            putExtra(Intent.EXTRA_TEXT, issueText)  // email body
            // Set the type of the email
            type = "message/rfc822" // This ensures only email apps are shown in the chooser
        }

        // Check if there's an email client installed before starting the intent
        if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send email"))
        } else {
            // Handle the case where no email client is installed
            // You could show a message or guide the user to install an email app
        }
    }
}