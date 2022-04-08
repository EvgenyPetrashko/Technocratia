package com.template.technocratia

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.template.technocratia.data.database.AppDatabase
import com.template.technocratia.data.database.UserDao
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.entities.UserDB
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestDatabase {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(
            context, AppDatabase::class.java, "fake-user-db").build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun readUsers() {
        val users = userDao.getAll()
        println(users)
        assertNotNull(users)
    }

    @Test
    @Throws(Exception::class)
    fun writeUser(){
        val testUser = UserDB(
            photo = "",
            fullName = "dfsdsdfg",
            dateOfBirth = "02.34.1323",
            phoneNumber = "7489543757",
            location = "njwefnjnfskjs",
            coordinates = "14.32,24.21"
        )
        val id = userDao.insert(testUser)
        println(id)
        assertNotNull(id)
    }
}