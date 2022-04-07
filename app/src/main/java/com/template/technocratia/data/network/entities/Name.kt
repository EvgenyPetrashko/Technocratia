package com.template.technocratia.data.network.entities

data class Name(
    val first: String,
    val last: String,
    val title: String,
){
    override fun toString(): String = "$title $first $last"
}