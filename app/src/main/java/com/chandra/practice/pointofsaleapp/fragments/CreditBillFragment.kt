package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.adapter.ProductAdapter
import com.chandra.practice.pointofsaleapp.data.AddProductItemDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentCreditBillBinding
import com.chandra.practice.pointofsaleapp.util.FullScreenAlertDialogFragment
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.chandra.practice.pointofsaleapp.util.toastMsg
import com.google.android.material.textfield.TextInputEditText


class CreditBillFragment : Fragment() , FullScreenAlertDialogFragment.OnProductAddedListener ,
                           ProductAdapter.OnProductAdapterListener {
    private lateinit var creditBillBinding : FragmentCreditBillBinding
    private var addedProductList : MutableList<AddProductItemDetails> = mutableListOf()
    private lateinit var productAdapter : ProductAdapter

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        creditBillBinding = FragmentCreditBillBinding.inflate(inflater , container , false)
        // Set up the RecyclerView
        productAdapter = ProductAdapter(this , addedProductList)
        creditBillBinding.productsRecyclerView.layoutManager = LinearLayoutManager(context)
        creditBillBinding.productsRecyclerView.adapter = productAdapter

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
        updateRecyclerViewVisibility()
        return creditBillBinding.root
    }

    private fun openFullScreenAlertDialog() {
        val dialog = FullScreenAlertDialogFragment()
        dialog.show(childFragmentManager , "FullScreenDialog")
    }

    override fun onProductAdded(product : AddProductItemDetails) {
        addedProductList.add(product)
        productAdapter.notifyItemInserted(addedProductList.size - 1) // Notify adapter of new item
        updateRecyclerViewVisibility() // Update visibility based on list size
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

    override fun onProductItemDelete(product : AddProductItemDetails , position : Int) {
        requireContext().toastMsg("Item Removed" , requireContext())
        addedProductList.removeAt(position)
        productAdapter.notifyItemRemoved(position)
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

