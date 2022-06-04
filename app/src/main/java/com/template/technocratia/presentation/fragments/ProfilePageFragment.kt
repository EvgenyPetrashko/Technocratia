package com.template.technocratia.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.template.technocratia.R
import com.template.technocratia.databinding.ProfilePageScreenBinding
import com.template.technocratia.presentation.activities.MainActivity
import com.template.technocratia.presentation.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilePageFragment : Fragment() {
    private var binding : ProfilePageScreenBinding? = null

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ProfilePageScreenBinding.inflate(inflater, container, false)
        this.binding = binding
        setHasOptionsMenu(true)

        if (mainActivityViewModel.users.value == null)
            mainActivityViewModel.refreshUser()

        binding.refreshProfileBtn.setOnClickListener {
            mainActivityViewModel.refreshUser()
        }

        binding.saveProfileBtn.setOnClickListener {
            mainActivityViewModel.saveUser()
        }

        binding.profileCallBtn.setOnClickListener {
            dialPhoneNumber()
        }

        binding.profileMapBtn.setOnClickListener {
            showMap()
        }

        mainActivityViewModel.users.observe(viewLifecycleOwner) {
            binding.profileFullName.text = it.fullName
            binding.profileDateBirth.text = it.dateOfBirth
            Glide.with(this)
                .load(it.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.profileImg)
            binding.profilePhone.text = it.phoneNumber
            binding.profileLocation.text = it.location
            binding.profileCoordinates.text = it.coordinates
        }

        mainActivityViewModel.errors.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        mainActivityViewModel.loadingState.observe(viewLifecycleOwner) {
            binding.refreshProfileBtn.isEnabled = !it
        }

        mainActivityViewModel.savedState.observe(viewLifecycleOwner) {
            binding.saveProfileBtn.isEnabled = !it
        }

        (activity as MainActivity).setSupportActionBar(binding.mainActivityToolbar)

        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.gotoCollection -> {
                findNavController().navigate(
                    R.id.action_profilePageFragment_to_listUsersFragment
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}