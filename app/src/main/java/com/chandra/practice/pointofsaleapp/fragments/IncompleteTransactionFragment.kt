package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.adapter.IncompleteTransactionAdapter
import com.chandra.practice.pointofsaleapp.adapter.IncompleteTransactionAdapter.OnIncompleteTransactionItemsClickListeners
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentIncompleteTransactionBinding
import com.chandra.practice.pointofsaleapp.repository.NewGenerateBillRepository
import com.chandra.practice.pointofsaleapp.roomdb.AppDatabase
import com.chandra.practice.pointofsaleapp.util.CustomMaterialAlertDialog
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.chandra.practice.pointofsaleapp.viewmodel.NewGenerateBillViewModel
import com.chandra.practice.pointofsaleapp.viewmodelfactory.NewGenerateBillViewModelFactory

class IncompleteTransactionFragment : Fragment() , OnIncompleteTransactionItemsClickListeners {
    private lateinit var incompleteTransactionBinding : FragmentIncompleteTransactionBinding
    private lateinit var incompleteTransactionAdapter : IncompleteTransactionAdapter
    private  var incompleteTransactionList : MutableList<NewGenerateBillCustomerDetails> = mutableListOf()
    private lateinit var newGenerateBillViewModel : NewGenerateBillViewModel

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        incompleteTransactionBinding = FragmentIncompleteTransactionBinding.inflate(layoutInflater)
        val userDao = AppDatabase.getDatabase(requireContext()).newGenerateBillDao()
        val repository = NewGenerateBillRepository(userDao)
        val viewModelFactory = NewGenerateBillViewModelFactory(repository)
        newGenerateBillViewModel = ViewModelProvider(this , viewModelFactory)[NewGenerateBillViewModel::class.java]
        newGenerateBillViewModel.allUsers.observe(viewLifecycleOwner) {
            Log.d("TAG" , "onCreateView: ${it.size}")
        incompleteTransactionList.clear()
            if (it.isNotEmpty()){
                for(item in it.iterator()){
                    if(item.fullyNotPaid){
                        incompleteTransactionList.add(item)
                        incompleteTransactionBinding.tvNotFound.visibility = View.GONE
                        incompleteTransactionBinding.incompleteTransactionRecyclerView.visibility = View.VISIBLE
                        incompleteTransactionAdapter = IncompleteTransactionAdapter(this, incompleteTransactionList)
                        incompleteTransactionBinding.incompleteTransactionRecyclerView.layoutManager = LinearLayoutManager(context)
                        incompleteTransactionBinding.incompleteTransactionRecyclerView.adapter = incompleteTransactionAdapter
                    }
                }
            }else{
                  incompleteTransactionBinding.tvNotFound.visibility = View.VISIBLE
                  incompleteTransactionBinding.incompleteTransactionRecyclerView.visibility = View.GONE
            }
        }
        incompleteTransactionBinding.ivExitIcon.setOnSingleClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        return incompleteTransactionBinding.root
    }

    override fun onClickUpdateTheIncompleteTransaction(
        position : Int ,
        newGenerateBillCustomerDetails : NewGenerateBillCustomerDetails ,
                                                      ) {
        updateTheIncompleteTransactionAmount()
    }
    private fun updateTheIncompleteTransactionAmount() {
        // Create the CustomAlertDialog and pass necessary arguments
        CustomMaterialAlertDialog(
                context = requireContext(),
                title = "Update Product Amount",
                message = "Please Enter the product Amount below.",
                hint = "Product Amount *",
                positiveButtonAction = { inputText ->
                    // Handle the input text from the dialog
                    Log.d("DialogInput" , "Input: $inputText")
                }).show()

    }
}