package com.template.technocratia.domain.entities

data class User(
    val photo: String,
    val fullName: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val location: String,
    val coordinates: String,
)
