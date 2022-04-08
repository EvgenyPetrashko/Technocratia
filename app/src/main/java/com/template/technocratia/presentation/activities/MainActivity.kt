package com.template.technocratia.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.template.technocratia.R
import com.template.technocratia.domain.usecase.GetUserFromServerUseCase
import com.template.technocratia.presentation.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var fab: FloatingActionButton
    private lateinit var saveBtn: MaterialButton

    @Inject
    lateinit var getUserFromServerUseCase: GetUserFromServerUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (mainActivityViewModel.users.value == null)
            mainActivityViewModel.refreshUser()

        fab = findViewById(R.id.refreshProfileBtn)

        fab.setOnClickListener {
            mainActivityViewModel.refreshUser()
        }

        saveBtn = findViewById(R.id.saveProfileBtn)

        saveBtn.setOnClickListener {
            mainActivityViewModel.saveUser()
        }

        mainActivityViewModel.errors.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        mainActivityViewModel.loadingState.observe(this) {
            fab.isEnabled = !it
        }

        mainActivityViewModel.savedState.observe(this) {
            saveBtn.isEnabled = !it
        }

        setSupportActionBar(findViewById(R.id.mainActivityToolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.gotoCollection -> {
                val intent = Intent(this, ListUsersActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}