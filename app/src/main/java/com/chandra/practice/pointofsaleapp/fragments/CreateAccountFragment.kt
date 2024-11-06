package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.CreateAccountDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentCreateAccountBinding
import com.chandra.practice.pointofsaleapp.repository.CreateAccountRepository
import com.chandra.practice.pointofsaleapp.roomdb.AppDatabase
import com.chandra.practice.pointofsaleapp.util.CustomBottomSheet
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.chandra.practice.pointofsaleapp.util.showErrorSnackBar
import com.chandra.practice.pointofsaleapp.util.storeCustomerValues
import com.chandra.practice.pointofsaleapp.util.toastMsg
import com.chandra.practice.pointofsaleapp.viewmodel.CreateAccountViewModel
import com.chandra.practice.pointofsaleapp.viewmodelfactory.CreateAccountViewModelFactory


class CreateAccountFragment : Fragment() , CustomBottomSheet.BottomSheetClickListener {
    private lateinit var createAccountBinding : FragmentCreateAccountBinding
    private lateinit var createAccountViewModel : CreateAccountViewModel


    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        createAccountBinding = FragmentCreateAccountBinding.inflate(layoutInflater)
        val userDao = AppDatabase.getDatabase(requireContext()).createAccountDao()
        val repository = CreateAccountRepository(userDao)
        val viewModelFactory = CreateAccountViewModelFactory(repository)
        createAccountViewModel =
            ViewModelProvider(this , viewModelFactory)[CreateAccountViewModel::class.java]

        createAccountBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        createAccountBinding.signaturePhotoText.setOnSingleClickListener {
            bottomSheetSelection()
        }
        createAccountBinding.createButton.setOnSingleClickListener {
            val isValid = validateForm()
            if (isValid) {
                val user = CreateAccountDetails(
                        id ,
                        billRules = createAccountBinding.fullNameEditText.text.toString().trim() ,
                        businessAddress = createAccountBinding.businessAddressEditText.text.toString()
                                .trim() ,
                        businessName = createAccountBinding.businessNameEditText.text.toString()
                                .trim() ,
                        customerSignaturePhoto = "empty" ,
                        customerFullName = createAccountBinding.fullNameEditText.text.toString()
                                .trim() ,
                        emailAddress = createAccountBinding.emailAddressEditText.text.toString()
                                .trim() ,
                        gSTNumber = createAccountBinding.gstNumberEditText.text.toString().trim() ,
                        noteInfo = createAccountBinding.notesEditText.text.toString().trim() ,
                        password = createAccountBinding.passwordEditText.text.toString().trim() ,
                        phoneNumber = createAccountBinding.phoneNumberEditText.text.toString().trim()
                                               )
                createAccountViewModel.insert(user)
                //store values in SharedPreference
                storeCustomerValues(requireContext(),createAccountBinding.emailAddressEditText.text.toString(),createAccountBinding.passwordEditText.text.toString().trim(),true)
                showErrorSnackBar(requireView(),"Account created successfully!")
                findNavController().navigate(R.id.loginFragment,null,NavOptions.Builder().setPopUpTo(R.id.createAccountFragment , true).build())

            }
        }

        return createAccountBinding.root
    }


    private fun bottomSheetSelection() {
        CustomBottomSheet(requireContext()).showBottomSheet("Camera" , "Upload Image" , requireContext() , this)
    }

    override fun onChooseClick(selectedText : String) {
        if (selectedText == "Camera") captureSignatureImage()
        else selectSignatureFromFileManager()
        requireContext().toastMsg(selectedText , requireContext())
    }

    private fun captureSignatureImage() {

    }
    private fun selectSignatureFromFileManager() {

    }

    private fun validateForm() : Boolean {
        var isValid = true

        // Full Name validation
        if (createAccountBinding.fullNameEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.fullNameInputLayout.error = "Full Name is required"
            isValid = false
        } else {
            createAccountBinding.fullNameInputLayout.error = null
        }

        // Business Name validation
        if (createAccountBinding.businessNameEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.businessNameInputLayout.error = "Business Name is required"
            isValid = false
        } else {
            createAccountBinding.businessNameInputLayout.error = null
        }

        // Phone Number validation (basic check for non-empty and length)
        if (createAccountBinding.phoneNumberEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.phoneNumberInputLayout.error = "Phone Number is required"
            isValid = false
        } else if (createAccountBinding.phoneNumberEditText.text!!.length < 10) {
            createAccountBinding.phoneNumberInputLayout.error =
                "Phone Number must be at least 10 digits"
            isValid = false
        } else {
            createAccountBinding.phoneNumberInputLayout.error = null
        }

        // Email Address validation (basic email format check)
        val email = createAccountBinding.emailAddressEditText.text.toString().trim()
        if (email.isEmpty()) {
            createAccountBinding.emailAddressInputLayout.error = "Email Address is required"
            isValid = false
        } else if (! android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            createAccountBinding.emailAddressInputLayout.error = "Invalid Email Address"
            isValid = false
        } else {
            createAccountBinding.emailAddressInputLayout.error = null
        }

        // Password validation (at least 6 characters)
        if (createAccountBinding.passwordEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.passwordInputLayout.error = "Password is required"
            isValid = false
        } else if (createAccountBinding.passwordEditText.text!!.length < 6) {
            createAccountBinding.passwordInputLayout.error =
                "Password must be at least 6 characters"
            isValid = false
        } else {
            createAccountBinding.passwordInputLayout.error = null
        }

        // GST Number validation (optional, but check if not empty)
        if (createAccountBinding.gstNumberEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.gstNumberInputLayout.error = "GST Number is required"
            isValid = false
        } else {
            createAccountBinding.gstNumberInputLayout.error = null
        }

        // Bill Rules validation (optional, check if not empty)
        if (createAccountBinding.billRulesEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.billRulesInputLayout.error = "Bill Rules are required"
            isValid = false
        } else {
            createAccountBinding.billRulesInputLayout.error = null
        }

        // Notes validation (optional)
        if (createAccountBinding.notesEditText.text.toString().trim().isEmpty()) {
            createAccountBinding.notesInputLayout.error = "Notes cannot be empty"
            isValid = false
        } else {
            createAccountBinding.notesInputLayout.error = null
        }

        // Disable or enable create button based on validation status
        createAccountBinding.createButton.isEnabled = isValid

        return isValid
    }
}
