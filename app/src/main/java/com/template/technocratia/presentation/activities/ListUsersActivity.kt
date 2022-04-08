package com.template.technocratia.presentation.activities

import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.technocratia.R
import com.template.technocratia.presentation.viewmodels.ListUsersViewModel
import com.template.technocratia.presentation.utils.UserRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUsersActivity : AppCompatActivity() {

    private lateinit var usersRv: RecyclerView
    private val listUsersViewModel: ListUsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        listUsersViewModel.getUsers()

        usersRv = findViewById(R.id.usersRv)
        usersRv.layoutManager = LinearLayoutManager(this)
        usersRv.adapter = UserRecyclerViewAdapter(listOf())

        listUsersViewModel.users.observe(this) {
            usersRv.adapter = UserRecyclerViewAdapter(it)
        }


    }
}