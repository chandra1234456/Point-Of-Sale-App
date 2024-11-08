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

class CompleteTransactionAdapter(private val listener : OnIncompleteTransactionItemsClickListeners ,
private val list : List<NewGenerateBillCustomerDetails> ,
) :
RecyclerView.Adapter<CompleteTransactionAdapter.CompleteTransactionItemsViewHolder>() {
    interface OnIncompleteTransactionItemsClickListeners {
        fun onClickDeleteCompleteTransaction(position : Int , newGenerateBillCustomerDetails : NewGenerateBillCustomerDetails)
    }

    class CompleteTransactionItemsViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView) {
        val ivDeleteTransaction : ImageView = itemView.findViewById(R.id.ivDeleteTheTransaction)
        val tvTransactionDateTime : MaterialTextView = itemView.findViewById(R.id.tvTransactionDateTime)
        val tvCustomerName : MaterialTextView = itemView.findViewById(R.id.tvCustomerName)
        val tvPhoneNumber : MaterialTextView = itemView.findViewById(R.id.tvPhoneNumber)
        val tvTotalAmount : MaterialTextView = itemView.findViewById(R.id.tvTotalAmount)
        val tvPaidAmount : MaterialTextView = itemView.findViewById(R.id.tvPaidAmount)
    }

    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : CompleteTransactionItemsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_complete_transaction_items , parent , false)
        return CompleteTransactionItemsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder : CompleteTransactionItemsViewHolder ,
        position : Int ,
                                 ) {
        val listItem = list[position]
        Log.d("TAG" , "onBindViewHolder: $listItem")
        holder.apply {
            tvTransactionDateTime.text = listItem.transactionDateTime
            tvCustomerName.text = listItem.customerName
            tvPhoneNumber.text = listItem.phoneNumber
            tvPaidAmount.text = listItem.paidAmount
            tvTotalAmount.text = listItem.totalAmount
            ivDeleteTransaction.setOnSingleClickListener {
                listener.onClickDeleteCompleteTransaction(position,listItem)
            }
        }

    }


    override fun getItemCount() : Int {
        return list.size
    }
}