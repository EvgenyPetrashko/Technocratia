package com.template.technocratia.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.template.technocratia.R


class ProfileAdditionalInfo: Fragment() {
    private lateinit var userPhoneNumber: TextView
    private lateinit var userLocation: TextView
    private lateinit var userCoordinates: TextView
    private lateinit var userDialBtn: ImageButton
    private lateinit var userMapBtn: ImageButton
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_additional_info, container, false)
        userPhoneNumber = view.findViewById(R.id.profilePhone)
        userLocation = view.findViewById(R.id.profileLocation)
        userCoordinates = view.findViewById(R.id.profileCoordinates)

        userDialBtn = view.findViewById(R.id.profileCallBtn)
        userDialBtn.setOnClickListener{
            dialPhoneNumber()
        }

        userMapBtn = view.findViewById(R.id.profileMapBtn)
        userMapBtn.setOnClickListener{
            showMap()
        }

        userViewModel.users.observe(viewLifecycleOwner) {
            userPhoneNumber.text = it.phoneNumber
            userLocation.text = it.location
            userCoordinates.text = it.coordinates
        }

        return view
    }

    private fun dialPhoneNumber() {
        val phoneNumber = userViewModel.users.value?.phoneNumber
        if (phoneNumber != null){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${phoneNumber}")
            startActivity(intent)
        }
    }

    private fun showMap(){
        val coordinates = userViewModel.users.value?.coordinates
        if (coordinates != null){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:$coordinates")
            startActivity(intent)
        }
    }
}