package com.template.technocratia.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.technocratia.databinding.ListUsersScreenBinding
import com.template.technocratia.presentation.utils.UserRecyclerViewAdapter
import com.template.technocratia.presentation.viewmodels.ListUsersViewModel

class ListUsersFragment: Fragment() {
    private val listUsersViewModel: ListUsersViewModel by activityViewModels()
    private var binding: ListUsersScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListUsersScreenBinding.inflate(inflater, container, false)
        this.binding = binding

        listUsersViewModel.getUsers()

        binding.usersRv.layoutManager = LinearLayoutManager(context)
        binding.usersRv.adapter = UserRecyclerViewAdapter(listOf())

        listUsersViewModel.users.observe(viewLifecycleOwner) {
            binding.usersRv.adapter = UserRecyclerViewAdapter(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}