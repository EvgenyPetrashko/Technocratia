package com.template.technocratia.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.template.technocratia.R
import com.template.technocratia.presentation.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileGeneralInfo : Fragment() {
    private lateinit var userFullName: TextView
    private lateinit var userDateBirth: TextView
    private lateinit var userImgProfile: ImageView
    private lateinit var userPhoneNumber: TextView
    private lateinit var userLocation: TextView
    private lateinit var userCoordinates: TextView
    private lateinit var userDialBtn: ImageButton
    private lateinit var userMapBtn: ImageButton
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_general_info, container, false)
        userFullName = view.findViewById(R.id.profileFullName)
        userDateBirth = view.findViewById(R.id.profileDateBirth)
        userImgProfile = view.findViewById(R.id.profileImg)
        userPhoneNumber = view.findViewById(R.id.profilePhone)
        userLocation = view.findViewById(R.id.profileLocation)
        userCoordinates = view.findViewById(R.id.profileCoordinates)

        userDialBtn = view.findViewById(R.id.profileCallBtn)
        userDialBtn.setOnClickListener {
            dialPhoneNumber()
        }

        userMapBtn = view.findViewById(R.id.profileMapBtn)
        userMapBtn.setOnClickListener {
            showMap()
        }

        mainActivityViewModel.users.observe(viewLifecycleOwner) {
            userFullName.text = it.fullName
            userDateBirth.text = it.dateOfBirth
            Glide.with(this)
                .load(it.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(userImgProfile)
            userPhoneNumber.text = it.phoneNumber
            userLocation.text = it.location
            userCoordinates.text = it.coordinates
        }
        return view
    }

    private fun dialPhoneNumber() {
        val phoneNumber = mainActivityViewModel.users.value?.phoneNumber
        if (phoneNumber != null) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${phoneNumber}")
            startActivity(intent)
        }
    }

    private fun showMap() {
        val coordinates = mainActivityViewModel.users.value?.coordinates
        if (coordinates != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:$coordinates")
            startActivity(intent)
        }
    }
}