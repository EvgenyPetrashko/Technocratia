package com.template.technocratia.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserStored(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val photo: String,
    val fullName: String,
)