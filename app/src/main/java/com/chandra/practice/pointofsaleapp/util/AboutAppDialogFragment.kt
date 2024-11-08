package com.chandra.practice.pointofsaleapp.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.adapter.AboutAppAdapter
import com.chandra.practice.pointofsaleapp.databinding.FragementDialogAboutAppBinding

class AboutAppDialogFragment : DialogFragment(){
    private lateinit var aboutAppBinding : FragementDialogAboutAppBinding
    private lateinit var aboutAppAdapter: AboutAppAdapter

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        aboutAppBinding = FragementDialogAboutAppBinding.inflate(layoutInflater)
        aboutAppBinding.aboutAppRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        aboutAppAdapter = AboutAppAdapter(requireContext(), createFeatureList())
        aboutAppBinding.aboutAppRecyclerView.adapter = aboutAppAdapter
        return aboutAppBinding.root
    }
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL , R.style.AppTheme_FullScreenDialog)
    }
    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        aboutAppBinding.toolbar.setNavigationOnClickListener {
            dismiss()
        }
        aboutAppBinding.toolbar.setTitle("About App");
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