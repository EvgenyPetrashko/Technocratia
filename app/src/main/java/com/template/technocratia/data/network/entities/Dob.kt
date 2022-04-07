package com.template.technocratia.data.network.entities

data class Dob(
    val age: Int,
    val date: String
){
    override fun toString(): String = date
}