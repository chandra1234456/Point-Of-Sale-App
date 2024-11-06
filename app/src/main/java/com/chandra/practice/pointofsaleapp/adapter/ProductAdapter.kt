package com.chandra.practice.pointofsaleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.CustomerProductDetail
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.google.android.material.textview.MaterialTextView

class ProductAdapter(
    private val listner : OnProductAdapterListener ,
    private val productList : List<CustomerProductDetail> ,
                    ) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    interface OnProductAdapterListener {
        fun onProductItemDelete(product : CustomerProductDetail , position : Int)
    }

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productName : MaterialTextView = itemView.findViewById(R.id.tvProductName)
        val productPrice : MaterialTextView = itemView.findViewById(R.id.tvProductPrice)
        val quantity : MaterialTextView = itemView.findViewById(R.id.tvQuantity)
        val comments : MaterialTextView = itemView.findViewById(R.id.tvComments)
        val ivDelete : ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product , parent , false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder : ProductViewHolder , position : Int) {
        val product = productList[position]
        holder.productName.text = product.productName
        holder.productPrice.text = "Price: ${product.productPrice}"
        holder.quantity.text = "Quantity: ${product.productQuantity}"
        holder.comments.text = "Comments: ${product.comments}" // If you want to show comments
        holder.ivDelete.setOnSingleClickListener {
            listner.onProductItemDelete(product , position)
        }
    }

    override fun getItemCount() : Int = productList.size
}
