package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.LoginResult
import com.chandra.practice.pointofsaleapp.databinding.FragmentLoginBinding
import com.chandra.practice.pointofsaleapp.util.retrieveTheCustomerValues
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.chandra.practice.pointofsaleapp.util.showCustomActionSnackBar
import com.chandra.practice.pointofsaleapp.util.showErrorSnackBar
import com.chandra.practice.pointofsaleapp.util.toastMsg

class LoginFragment : Fragment() {
    private lateinit var loginBinding : FragmentLoginBinding
    private lateinit var result :LoginResult

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
         result = retrieveTheCustomerValues(requireContext())
    }
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater)
        loginBinding.tieEmail.setText("")
        loginBinding.tiePassword.setText("")
        loginBinding.btnLogin.setOnClickListener {
            val emailInput = loginBinding.tieEmail.text.toString().trim()
            val passwordInput = loginBinding.tiePassword.text.toString().trim()
            // Check if the result (account info) is empty
            if (result.email.equals("empty")||result.password.equals("empty")) {
                requireContext().toastMsg("Please Create Account", requireContext())
                return@setOnClickListener // Early return, no need to proceed further
            }

            // Check if email and password match
            val storedEmail = result.email
            val storedPassword = result.password

            if (emailInput == storedEmail && passwordInput == storedPassword) {
                findNavController().navigate(R.id.homeFragment)
            } else {
                // Provide a specific message for either email or password mismatch
                when {
                    emailInput != storedEmail ->  requireContext().toastMsg("Please Enter Proper Email Address", requireContext())
                    passwordInput != storedPassword -> requireContext().toastMsg("Please Enter Proper Password Address", requireContext())
                }
            }
        }
        loginBinding.tvContinue.setOnSingleClickListener {
            //showErrorSnackBar(requireView(),"Please Click One More Time")
            showCustomActionSnackBar(requireView(),"Do You Want Undo","UNDO") {
             requireContext().toastMsg("REVERTED",requireContext())
            }
        }
        loginBinding.tvCreateAcc.setOnSingleClickListener {
            findNavController().navigate(R.id.createAccountFragment)
        }
        return loginBinding.root
    }
}