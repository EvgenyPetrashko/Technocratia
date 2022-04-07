package com.template.technocratia.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.template.technocratia.R
import com.template.technocratia.domain.usecase.GetUserUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var getUserUseCase: GetUserUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.refresh_profile_btn).setOnClickListener {
            runBlocking {
                println("Hello1")
                println(getUserUseCase.execute())
                println("Hello2")
            }
        }
    }
}