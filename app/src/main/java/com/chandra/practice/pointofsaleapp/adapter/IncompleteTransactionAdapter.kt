package com.chandra.practice.pointofsaleapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.google.android.material.textview.MaterialTextView

class IncompleteTransactionAdapter(
    private val listener : OnIncompleteTransactionItemsClickListeners ,
    private val list : List<NewGenerateBillCustomerDetails> ,
                                  ) :
        RecyclerView.Adapter<IncompleteTransactionAdapter.IncompleteTransactionItemsViewHolder>() {
    interface OnIncompleteTransactionItemsClickListeners {
        fun onClickUpdateTheIncompleteTransaction(position : Int , newGenerateBillCustomerDetails : NewGenerateBillCustomerDetails)
    }

    class IncompleteTransactionItemsViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView) {
        val ivEditTransaction : ImageView = itemView.findViewById(R.id.ivEditTheTransaction)
        val tvTransactionDateTime : MaterialTextView = itemView.findViewById(R.id.tvTransactionDateTime)
        val tvCustomerName : MaterialTextView = itemView.findViewById(R.id.tvCustomerName)
        val tvPhoneNumber : MaterialTextView = itemView.findViewById(R.id.tvPhoneNumber)
        val tvTotalAmount : MaterialTextView = itemView.findViewById(R.id.tvTotalAmount)
        val tvPaidAmount : MaterialTextView = itemView.findViewById(R.id.tvPaidAmount)
        val tvRemainingAmount : MaterialTextView = itemView.findViewById(R.id.tvRemainingAmount)
    }

    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : IncompleteTransactionItemsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_incomplete_transaction_items , parent , false)
        return IncompleteTransactionItemsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder : IncompleteTransactionItemsViewHolder ,
        position : Int ,
                                 ) {
        val listItem = list[position]
        Log.d("TAG" , "onBindViewHolder: $listItem")
        holder.apply {
            tvTransactionDateTime.text = listItem.transactionDateTime
            tvCustomerName.text = listItem.customerName
            tvPhoneNumber.text = listItem.phoneNumber
            tvRemainingAmount.text = listItem.remainingAmount
            tvPaidAmount.text = listItem.paidAmount
            tvTotalAmount.text = listItem.totalAmount
        }
        holder.ivEditTransaction.setOnSingleClickListener {
            listener.onClickUpdateTheIncompleteTransaction(position , listItem)
        }
    }


    override fun getItemCount() : Int {
        return list.size
    }
}