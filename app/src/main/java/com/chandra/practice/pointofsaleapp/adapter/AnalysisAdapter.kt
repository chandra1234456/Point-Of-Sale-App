package com.chandra.practice.pointofsaleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails

class AnalysisAdapter(context : Context,val list : List<NewGenerateBillCustomerDetails>):RecyclerView.Adapter<AnalysisAdapter.AnalysisViewHolder>() {
    class AnalysisViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {

    }
    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int ,
                                   ) : AnalysisViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_analysis_items , parent , false)
        return AnalysisViewHolder(view)
    }




    override fun onBindViewHolder(holder : AnalysisViewHolder , position : Int) {

    }

    override fun getItemCount() : Int {
        return list.size
    }
}