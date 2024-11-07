package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.adapter.ProductAdapter
import com.chandra.practice.pointofsaleapp.data.CustomerProductDetail
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentCreditBillBinding
import com.chandra.practice.pointofsaleapp.repository.CreateAccountRepository
import com.chandra.practice.pointofsaleapp.repository.NewGenerateBillRepository
import com.chandra.practice.pointofsaleapp.roomdb.AppDatabase
import com.chandra.practice.pointofsaleapp.util.FullScreenAlertDialogFragment
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.chandra.practice.pointofsaleapp.util.showErrorSnackBar
import com.chandra.practice.pointofsaleapp.util.toastMsg
import com.chandra.practice.pointofsaleapp.viewmodel.CreateAccountViewModel
import com.chandra.practice.pointofsaleapp.viewmodel.NewGenerateBillViewModel
import com.chandra.practice.pointofsaleapp.viewmodelfactory.CreateAccountViewModelFactory
import com.chandra.practice.pointofsaleapp.viewmodelfactory.NewGenerateBillViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import kotlin.math.log


class CreditBillFragment : Fragment() , FullScreenAlertDialogFragment.OnProductAddedListener ,
                           ProductAdapter.OnProductAdapterListener {
    private lateinit var creditBillBinding : FragmentCreditBillBinding
    private var addedProductList : MutableList<CustomerProductDetail> = mutableListOf()
    private lateinit var productAdapter : ProductAdapter
    private lateinit var newGenerateBillViewModel : NewGenerateBillViewModel
    private lateinit var newGenerateBillCustomerDetails : NewGenerateBillCustomerDetails
    private var totalPrice = 0
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        creditBillBinding = FragmentCreditBillBinding.inflate(inflater , container , false)
        val userDao = AppDatabase.getDatabase(requireContext()).newGenerateBillDao()
        val repository = NewGenerateBillRepository(userDao)
        val viewModelFactory = NewGenerateBillViewModelFactory(repository)
        newGenerateBillViewModel = ViewModelProvider(this , viewModelFactory)[NewGenerateBillViewModel::class.java]

        // Set up the RecyclerView
        productAdapter = ProductAdapter(this , addedProductList)
        creditBillBinding.productsRecyclerView.layoutManager = LinearLayoutManager(context)
        creditBillBinding.productsRecyclerView.adapter = productAdapter

        val totalAmount = productAdapter.calculateTotalAmount()
        Log.d("TAG" , "totalPrice: $totalAmount")
        val totalAmountStr = if (totalAmount == BigDecimal.ZERO) "0" else totalAmount.toPlainString()
        creditBillBinding.tvTotalAmount.text = "Total Amount $totalAmountStr"

        //Calculating Total Products Cost
        for (i in addedProductList.iterator()) {
            totalPrice += i.productPrice.toInt()
        }

        // Handle the add item button click
        creditBillBinding.addItemButton.setOnSingleClickListener {
            openFullScreenAlertDialog()
        }
        creditBillBinding.tiePaymentMethod.setOnSingleClickListener {
            popUpMenuItems(creditBillBinding.tiePaymentMethod)
        }
        creditBillBinding.tiePaymentStatus.setOnSingleClickListener {
            popUpMenuItems(creditBillBinding.tiePaymentStatus)
        }
        creditBillBinding.generateBillButton.setOnSingleClickListener {
            if (creditBillBinding.tiePaymentStatus.text.toString()=="Partially Paid"){
                newGenerateBillCustomerDetails = NewGenerateBillCustomerDetails(
                        customerName = creditBillBinding.tieCustomerName.text.toString().trim() ,
                        customerProductDetails = addedProductList.toMutableList() ,
                        paidAmount = creditBillBinding.tiePaidAmount.text.toString().trim() ,
                        paymentMethod = creditBillBinding.tiePaymentMethod.text.toString().trim() ,
                        paymentStatus = creditBillBinding.tiePaymentStatus.text.toString().trim() ,
                        phoneNumber = creditBillBinding.tiePhoneNumber.text.toString().trim(),
                        fullyNotPaid = true, totalAmount = "", remainingAmount = "", transactionDateTime = ""
                                                                               )
                Log.d("TAG" , "onCreateView: $newGenerateBillCustomerDetails")
            }else {
                newGenerateBillCustomerDetails = NewGenerateBillCustomerDetails(
                        customerName = creditBillBinding.tieCustomerName.text.toString().trim() ,
                        customerProductDetails = addedProductList.toMutableList() ,
                        paidAmount = creditBillBinding.tiePaidAmount.text.toString().trim() ,
                        paymentMethod = creditBillBinding.tiePaymentMethod.text.toString().trim() ,
                        paymentStatus = creditBillBinding.tiePaymentStatus.text.toString().trim() ,
                        phoneNumber = creditBillBinding.tiePhoneNumber.text.toString().trim(),
                        fullyNotPaid = false , totalAmount = "", remainingAmount = "", transactionDateTime = ""
                                                                                   )
                Log.d("TAG" , "onCreateView: $newGenerateBillCustomerDetails")
            }
            newGenerateBillViewModel.insert(newGenerateBillCustomerDetails)
            showErrorSnackBar(requireView(),"Customer Bill Generated Successfully")
            findNavController().navigate(R.id.analysisFragment)
        }
        updateRecyclerViewVisibility()
        return creditBillBinding.root
    }

    private fun openFullScreenAlertDialog() {
        val dialog = FullScreenAlertDialogFragment()
        dialog.show(childFragmentManager , "FullScreenDialog")
    }

    override fun onProductAdded(product : CustomerProductDetail) {
        addedProductList.add(product)
        productAdapter.notifyItemInserted(addedProductList.size - 1) // Notify adapter of new item
        updateRecyclerViewVisibility() // Update visibility based on list size
        productAdapter.notifyDataSetChanged()
    }

    private fun updateRecyclerViewVisibility() {
        creditBillBinding.productsRecyclerView.visibility = if (addedProductList.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
        creditBillBinding.noProductsEmptyLL.visibility = if (addedProductList.isEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onClickProductItemDelete(product : CustomerProductDetail , position : Int) {
        requireContext().toastMsg("Item Removed" , requireContext())
        addedProductList.removeAt(position)
        productAdapter.notifyItemRemoved(position)
        productAdapter.notifyDataSetChanged()
    }

    private fun popUpMenuItems(editText : TextInputEditText) {
        // Creating the instance of PopupMenu
        val popup = PopupMenu(requireContext() , editText)
        if (editText.hint == "Payment Method *") {
            // Inflating the Popup using XML file
            popup.menuInflater.inflate(R.menu.popup_menu_payment_method , popup.menu)
        } else {
            popup.menuInflater.inflate(R.menu.popup_menu_payment_staus , popup.menu)
        }

        // Registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener { item ->
            editText.setText(item.title.toString())
            Toast.makeText(requireContext() , "You Clicked: ${item.title}" , Toast.LENGTH_SHORT)
                    .show()
            true
        }

        popup.show() // Showing popup menu
    }
}

