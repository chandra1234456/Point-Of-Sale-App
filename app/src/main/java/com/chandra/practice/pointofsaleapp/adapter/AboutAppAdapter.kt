package com.chandra.practice.pointofsaleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.AboutAppItemDetails
import com.google.android.material.textview.MaterialTextView

class AboutAppAdapter(private val context: Context ,
                      private val featureList: List<AboutAppItemDetails>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 0
    private val TYPE_FEATURE = 1

    // ViewHolder for feature item
    inner class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: MaterialTextView = itemView.findViewById(R.id.tvFeatureTitle)
        val descriptionTextView: MaterialTextView = itemView.findViewById(R.id.tvFeatureDescription)
    }

    // ViewHolder for header item
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTextView: MaterialTextView = itemView.findViewById(R.id.tvHeaderTitle)
    }


    // Creating new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int):  RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.item_about_app, parent, false)
            FeatureViewHolder(view)
        }
    }
    // Determine the item type (Header or Feature)
    override fun getItemViewType(position: Int): Int {
        return if (featureList[position].isHeader) TYPE_HEADER else TYPE_FEATURE
    }

    // Binding the data to the appropriate ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feature = featureList[position]
        if (getItemViewType(position) == TYPE_HEADER) {
            (holder as HeaderViewHolder).headerTextView.text = feature.title
        } else {
            (holder as FeatureViewHolder).titleTextView.text = feature.title
            holder.descriptionTextView.text = feature.description
        }
    }

    // Returns the number of items in the list
    override fun getItemCount(): Int {
        return featureList.size
    }
}