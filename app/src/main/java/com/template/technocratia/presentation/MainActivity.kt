package com.template.technocratia.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.template.technocratia.R
import com.template.technocratia.domain.usecase.GetUserUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var fab: FloatingActionButton

    @Inject
    lateinit var getUserUseCase: GetUserUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel.refreshUser()

        fab = findViewById(R.id.refresh_profile_btn)

        fab.setOnClickListener {
            userViewModel.refreshUser()
        }

        userViewModel.errors.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        userViewModel.loadingState.observe(this) {
            fab.isEnabled = !it
        }
    }
}