package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.adapter.CompleteTransactionAdapter
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentCompleteTransactionBinding
import com.chandra.practice.pointofsaleapp.repository.NewGenerateBillRepository
import com.chandra.practice.pointofsaleapp.roomdb.AppDatabase
import com.chandra.practice.pointofsaleapp.util.toastMsg
import com.chandra.practice.pointofsaleapp.viewmodel.NewGenerateBillViewModel
import com.chandra.practice.pointofsaleapp.viewmodelfactory.NewGenerateBillViewModelFactory

class CompleteTransactionFragment : Fragment(),CompleteTransactionAdapter.OnIncompleteTransactionItemsClickListeners {
private lateinit var completeTransactionBinding : FragmentCompleteTransactionBinding
    private lateinit var completeTransactionAdapter : CompleteTransactionAdapter
    private  var incompleteTransactionList : MutableList<NewGenerateBillCustomerDetails> = mutableListOf()
    private lateinit var newGenerateBillViewModel : NewGenerateBillViewModel
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        completeTransactionBinding = FragmentCompleteTransactionBinding.inflate(layoutInflater)
        val userDao = AppDatabase.getDatabase(requireContext()).newGenerateBillDao()
        val repository = NewGenerateBillRepository(userDao)
        val viewModelFactory = NewGenerateBillViewModelFactory(repository)
        newGenerateBillViewModel = ViewModelProvider(this , viewModelFactory)[NewGenerateBillViewModel::class.java]
        newGenerateBillViewModel.allUsers.observe(viewLifecycleOwner) {
            incompleteTransactionList.clear()
            incompleteTransactionList = it.toMutableList()
            completeTransactionAdapter = CompleteTransactionAdapter(this,it)
            completeTransactionBinding.completeTransactionRecyclerView.layoutManager = LinearLayoutManager(context)
            completeTransactionBinding.completeTransactionRecyclerView.adapter = completeTransactionAdapter
        }
        return completeTransactionBinding.root
    }

    override fun onClickDeleteCompleteTransaction(
        position : Int ,
        newGenerateBillCustomerDetails : NewGenerateBillCustomerDetails ,
                                                 ) {
        requireContext().toastMsg("delete",requireContext())
    }
}