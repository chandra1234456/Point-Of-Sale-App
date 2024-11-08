package com.chandra.practice.pointofsaleapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.practice.pointofsaleapp.R
import com.chandra.practice.pointofsaleapp.SettingsActivity
import com.chandra.practice.pointofsaleapp.adapter.ProfileItemsAdapter
import com.chandra.practice.pointofsaleapp.data.CreateAccountDetails
import com.chandra.practice.pointofsaleapp.data.ProfileItemsDetails
import com.chandra.practice.pointofsaleapp.databinding.FragmentProfileBinding
import com.chandra.practice.pointofsaleapp.repository.CreateAccountRepository
import com.chandra.practice.pointofsaleapp.roomdb.AppDatabase
import com.chandra.practice.pointofsaleapp.util.AboutAppDialogFragment
import com.chandra.practice.pointofsaleapp.util.setOnSingleClickListener
import com.chandra.practice.pointofsaleapp.util.showCustomAlertDialog
import com.chandra.practice.pointofsaleapp.viewmodel.CreateAccountViewModel
import com.chandra.practice.pointofsaleapp.viewmodelfactory.CreateAccountViewModelFactory

class ProfileFragment : Fragment(),ProfileItemsAdapter.OnProfileItemsClickListners {
    private lateinit var profileBinding : FragmentProfileBinding
    private lateinit var profileItemsAdapter : ProfileItemsAdapter
    private var profileList : MutableList<ProfileItemsDetails> = mutableListOf()
    private var sendProfileDetails : MutableList<CreateAccountDetails> = mutableListOf()
    private lateinit var createAccountViewModel : CreateAccountViewModel

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
                             ) : View {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)
        profileList = listOf(
                ProfileItemsDetails("Help & Support", R.drawable.ic_help , R.drawable.ic_right_icon),
                ProfileItemsDetails("About App", R.drawable.ic_about_app , R.drawable.ic_right_icon),
                ProfileItemsDetails("Logout", R.drawable.ic_help , R.drawable.ic_right_icon),
                ProfileItemsDetails("Settings", R.drawable.ic_about_app , R.drawable.ic_right_icon),
                            ).toMutableList()
        profileItemsAdapter = ProfileItemsAdapter(this,profileList)
        profileBinding.profileItemsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        profileBinding.profileItemsRecyclerview.adapter = profileItemsAdapter
        profileBinding.ivBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        profileBinding.btnEditProfile.setOnSingleClickListener {
            showCustomMaterialAlertDialog()
        }
        profileBinding.ivShareButton.setOnSingleClickListener {
            shareTheProfileDetails(sendProfileDetails)
        }
        return profileBinding.root
    }

    private fun shareTheProfileDetails(sendProfileDetails : List<CreateAccountDetails>) {
        for(item in sendProfileDetails.iterator()){
            // Combine the profile details into a string
            val profileDetails = "Customer Name: ${item.customerFullName}\nEmail: ${item.emailAddress}\nBusiness Name: ${item.businessName}\n" +
                    "Business Address: ${item.businessAddress}\n GST Number: ${item.gSTNumber}\nPhone Number: ${item.phoneNumber}\nNote Info: ${item.noteInfo}\nBill Rules: ${item.billRules}"
            // Create a sharing intent
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, profileDetails)
                type = "text/plain"
            }
            // Show a chooser so the user can pick an app to share the profile with
            startActivity(Intent.createChooser(shareIntent, "Share Profile via"))
        }




    }

    private fun showCustomMaterialAlertDialog() {
        showCustomAlertDialog(
                context = requireContext(),
                title = "Confirmation",
                message = "Do you want to Edit The Profile?",
                positiveButtonText = "Yes",
                negativeButtonText = "No",
                onPositiveClick = {
                    findNavController().navigate(R.id.editProfileFragment)
                }
                             )

    }

    override fun onClickEndIconRes(position : Int , profileItemsDetails : ProfileItemsDetails) {
        Toast.makeText(requireContext() , "$position" , Toast.LENGTH_SHORT).show()
        when(profileItemsDetails.imageIconName) {
            "About App" -> openAboutAppScreenAlertDialog()
            "Settings" -> {
                // Navigate to SettingsActivity using Intent
                val intent = Intent(requireContext() , SettingsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            "Help & Support" -> findNavController().navigate(R.id.contactUsFragment)

            else -> {
                // Handle any other cases, or log for debugging
                Log.d("ProfileItem" , "Unknown imageIconName: ${profileItemsDetails.imageIconName}")
            }
        }
    }
    private fun mapCreateAccountToProfileItem(createAccountDetails: CreateAccountDetails): CreateAccountDetails {
        return CreateAccountDetails(
                customerFullName = createAccountDetails.customerFullName,
                emailAddress = createAccountDetails.emailAddress,
                billRules = createAccountDetails.billRules,
                businessAddress = createAccountDetails.businessAddress,
                gSTNumber = createAccountDetails.gSTNumber,
                noteInfo = createAccountDetails.noteInfo,
                password = createAccountDetails.password,
                phoneNumber = createAccountDetails.phoneNumber,
                customerSignaturePhoto = createAccountDetails.customerSignaturePhoto,
                businessName = createAccountDetails.businessName

                                   )
    }
    private fun openAboutAppScreenAlertDialog() {
        val dialog = AboutAppDialogFragment()
        dialog.show(childFragmentManager , "AboutAppDialogFragment")
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val userDao = AppDatabase.getDatabase(requireContext()).createAccountDao()
        val repository = CreateAccountRepository(userDao)
        val viewModelFactory = CreateAccountViewModelFactory(repository)
        createAccountViewModel =
            ViewModelProvider(this , viewModelFactory)[CreateAccountViewModel::class.java]
        createAccountViewModel.getLoginUserDetails.observe(viewLifecycleOwner){
            Log.d("TAG" , "allUsers: $it")
            sendProfileDetails.clear()
            it?.forEach {
                sendProfileDetails.add(mapCreateAccountToProfileItem(it))
            }
            profileBinding.apply {
                for (item in it.iterator()) {
                    tvCustomerName.text = item.customerFullName
                    tvEmailAddress.text = item.emailAddress
                    tvMobileNumber.text = item.phoneNumber
                    tvGstNumber.text = item.gSTNumber
                    tvBusinessName.text = item.businessName
                }

            }
        }
    }

}