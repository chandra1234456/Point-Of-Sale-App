package com.chandra.practice.pointofsaleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.ProfileItemsDetails
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.google.android.material.textview.MaterialTextView

class ProfileItemsAdapter(
    private val listner : OnProfileItemsClickListners ,
    private val list : List<ProfileItemsDetails> ,
                         ) :
        RecyclerView.Adapter<ProfileItemsAdapter.ProfileItemsViewHolder>() {
    interface OnProfileItemsClickListners {
        fun onClickEndIconRes(position : Int , profileItemsDetails : ProfileItemsDetails)
    }

    class ProfileItemsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivStartIcon : ImageView = itemView.findViewById(R.id.ivStartIcon)
        val ivEndIcon : ImageView = itemView.findViewById(R.id.ivEndIcon)
        val tvIconText : MaterialTextView = itemView.findViewById(R.id.tvIconText)
    }

    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : ProfileItemsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_profile_items , parent , false)
        return ProfileItemsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder : ProfileItemsViewHolder ,
        position : Int ,
                                 ) {
        val listItem = list[position]
        holder.tvIconText.text = listItem.imageIconName
        holder.ivEndIcon.setImageResource(listItem.imageEndIconRes)
        holder.ivStartIcon.setImageResource(listItem.imageStartIconRes)
        holder.ivEndIcon.setOnSingleClickListener {
            listner.onClickEndIconRes(position,listItem)
        }
    }


    override fun getItemCount() : Int {
        return list.size
    }
}