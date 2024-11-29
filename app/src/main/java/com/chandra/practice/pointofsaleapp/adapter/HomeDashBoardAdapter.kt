package com.chandra.practice.pointofsaleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.HomeScreenDashBoardItemDetails
import com.google.android.material.textview.MaterialTextView

class HomeDashBoardAdapter(
    val context : Context ,
    private val list : List<HomeScreenDashBoardItemDetails> ,
                          ) :
        RecyclerView.Adapter<HomeDashBoardAdapter.HomeDashBoardItemsViewHolder>() {
    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : HomeDashBoardItemsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_home_dash_board , parent , false)
        return HomeDashBoardItemsViewHolder(view)
    }

    class HomeDashBoardItemsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivImageTopIcon : ImageView = itemView.findViewById(R.id.ivImageTopIcon)
        val tvTotalRevenueCount : MaterialTextView = itemView.findViewById(R.id.tvTotalRevenueCount)
        val tvTotalRevenueName : MaterialTextView = itemView.findViewById(R.id.tvTotalRevenueName)

    }

    override fun onBindViewHolder(
        holder : HomeDashBoardItemsViewHolder ,
        position : Int ,
                                 ) {
        val listItem = list[position]
        holder.tvTotalRevenueCount.text = listItem.amount
        holder.ivImageTopIcon.setImageResource(listItem.imageTopIcon)
        holder.tvTotalRevenueName.text = listItem.imageTopIconName
        val color = ContextCompat.getColor(context , listItem.imageTopIconNameTextColor)
        holder.tvTotalRevenueCount.setTextColor(color)
        holder.tvTotalRevenueName.setTextColor(color)

    }


    override fun getItemCount() : Int {
        return list.size
    }
}