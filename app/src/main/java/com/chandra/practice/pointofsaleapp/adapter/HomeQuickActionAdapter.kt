package com.chandra.practice.pointofsaleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.QuickActionItems
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class HomeQuickActionAdapter(
    val context : Context ,
    private val list : List<QuickActionItems> ,
                            ) :
        RecyclerView.Adapter<HomeQuickActionAdapter.HomeQuickActionViewHolder>() {
    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : HomeQuickActionViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_quick_action , parent , false)
        return HomeQuickActionViewHolder(view)
    }

    class HomeQuickActionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivImageIcon : ImageView = itemView.findViewById(R.id.ivImageIcon)
        val tvTransactionText : MaterialTextView = itemView.findViewById(R.id.tvTransactionText)
        val quickActionCardView : MaterialCardView= itemView.findViewById(R.id.quickActionCardView)

    }

    override fun onBindViewHolder(
        holder : HomeQuickActionViewHolder ,
        position : Int ,
                                 ) {
        val listItem = list[position]
        holder.tvTransactionText.text = listItem.transactionText
        holder.ivImageIcon.setImageResource(listItem.transactionImageRes)
        val color = ContextCompat.getColor(context , listItem.transactionTextColor)
        holder.tvTransactionText.setTextColor(color)
        holder.quickActionCardView.setBackgroundColor(color)

    }


    override fun getItemCount() : Int {
        return list.size
    }
}