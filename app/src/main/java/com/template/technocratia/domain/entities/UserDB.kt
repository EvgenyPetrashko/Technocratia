package com.template.technocratia.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDB(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    override val photo: String,
    override val fullName: String,
    override val dateOfBirth: String,
    override val phoneNumber: String,
    override val location: String,
    override val coordinates: String,
) : UserInterface