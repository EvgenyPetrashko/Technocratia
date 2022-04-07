package com.template.technocratia.data.network.entities

data class Coordinates(
    val latitude: String,
    val longitude: String
){
    override fun toString(): String = "$latitude,$longitude"
}