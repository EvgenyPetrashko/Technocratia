package com.template.technocratia.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.template.technocratia.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileGeneralInfo : Fragment() {
    private lateinit var userFullName: TextView
    private lateinit var userDateBirth: TextView
    private lateinit var userImgProfile: ImageView
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_general_info, container, false)
        userFullName = view.findViewById(R.id.profileFullName)
        userDateBirth = view.findViewById(R.id.profileDateBirth)
        userImgProfile = view.findViewById(R.id.profileImg)
        userViewModel.users.observe(viewLifecycleOwner) {
            userFullName.text = it.fullName
            userDateBirth.text = it.dateOfBirth
            Glide.with(this)
                .load(it.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(userImgProfile)
        }
        return view
    }
}