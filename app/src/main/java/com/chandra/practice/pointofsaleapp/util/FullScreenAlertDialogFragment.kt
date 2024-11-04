package com.chandra.practice.pointofsaleapp.util

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.data.AddProductItemDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentFullScreenAlertDialogBinding


class FullScreenAlertDialogFragment : DialogFragment() {
    private lateinit var fullScreenAlertDialogBinding : FragmentFullScreenAlertDialogBinding
    private val TAG : String = "example_dialog"

    interface OnProductAddedListener {
        fun onProductAdded(product: AddProductItemDetails)
    }

    private var listener: OnProductAddedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Ensure the host fragment implements the interface
        listener = parentFragment as? OnProductAddedListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnProductAddedListener")
        }
    }
    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        fullScreenAlertDialogBinding = FragmentFullScreenAlertDialogBinding.inflate(layoutInflater)
        return fullScreenAlertDialogBinding.root
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL , R.style.AppTheme_FullScreenDialog)
    }


    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        fullScreenAlertDialogBinding.toolbar.setNavigationOnClickListener {
            dismiss()
        }
        fullScreenAlertDialogBinding.toolbar.setTitle("Add Product Item");
        fullScreenAlertDialogBinding.toolbar.inflateMenu(R.menu.full_dilaog_menu);
        fullScreenAlertDialogBinding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    val data = AddProductItemDetails(
                                    fullScreenAlertDialogBinding.tieComments.text.toString(),
                                    fullScreenAlertDialogBinding.tieProductName.text.toString(),
                                    fullScreenAlertDialogBinding.tieProductPrice.text.toString(),
                                    fullScreenAlertDialogBinding.tieQuantity.text.toString()
                                                 )
                    Log.d(TAG , "onViewCreated: $data")
                    listener?.onProductAdded(data) // Send data back

                    dismiss()
                    true
                }

                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window !!.setLayout(width , height)
            dialog.window !!.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

}
