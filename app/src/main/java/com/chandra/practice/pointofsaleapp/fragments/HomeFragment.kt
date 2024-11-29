package com.chandra.practice.pointofsaleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.adapter.HomeDashBoardAdapter
import com.chandra.practice.pointofsaleapp.adapter.HomeQuickActionAdapter
import com.chandra.practice.pointofsaleapp.data.HomeScreenDashBoardItemDetails
import com.chandra.practice.pointofsaleapp.data.QuickActionItems
import com.chandra.practice.pointofsaleapp.databinding.FragmentHomeBinding
import com.chandra.practice.pointofsaleapp.util.getTodayDate
import com.chandra.practice.pointofsaleapp.util.toastMsg

class HomeFragment : Fragment(),HomeQuickActionAdapter.OnTransactionsItemClickListener {
    private lateinit var homeBinding : FragmentHomeBinding
    private lateinit var homeDashBoardAdapter : HomeDashBoardAdapter
    private lateinit var homeQuickActionAdapter : HomeQuickActionAdapter
    private var homeDashBoardList : MutableList<HomeScreenDashBoardItemDetails> = mutableListOf()
    private var homeQuickActionList : MutableList<QuickActionItems> = mutableListOf()
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        homeDashBoardList = listOf(
                HomeScreenDashBoardItemDetails(" ₹ 100.00" , R.drawable.ic_currency_rupee , "Total Revenue" , R.color.green) ,
                HomeScreenDashBoardItemDetails("10" , R.drawable.ic_receipt_long , "Total Transactions" , R.color.blue) ,
                HomeScreenDashBoardItemDetails("10" , R.drawable.ic_check_circle , "Completed" , R.color.brinjal) ,
                HomeScreenDashBoardItemDetails("10" , R.drawable.ic_pending_actions , "Pending" , R.color.orange)).toMutableList()
        homeDashBoardAdapter = HomeDashBoardAdapter(requireContext(),homeDashBoardList)
        homeBinding.dashBoardRecyclerview.layoutManager = GridLayoutManager(requireContext() , 2)
        homeBinding.dashBoardRecyclerview.adapter = homeDashBoardAdapter

        homeQuickActionList = listOf(
                QuickActionItems(R.drawable.ic_check_circle_outline , "Complete \n Transactions" , R.color.light_green,R.color.medium_green) ,
                QuickActionItems(R.drawable.ic_pending_actions , "Incomplete \n Transactions" ,R.color.light_orange,R.color.medium_orange)).toMutableList()
        homeQuickActionAdapter = HomeQuickActionAdapter(requireContext(),homeQuickActionList,this)
        homeBinding.quickActionsRecyclerview.layoutManager = GridLayoutManager(requireContext() , 2)
        homeBinding.quickActionsRecyclerview.adapter = homeQuickActionAdapter
        return homeBinding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        homeBinding.tvTodayDate.text = getTodayDate()
        homeBinding.tvBusinessName.text = "JAM JAM"
    }

    override fun transactionItemClick(position : Int , quickActionItems : QuickActionItems) {
        if (quickActionItems.transactionText == "Complete \n Transactions"){
            findNavController().navigate(R.id.completeTransactionFragment)
        }else{
            findNavController().navigate(R.id.incompleteTransactionFragment)
        }
    }
}