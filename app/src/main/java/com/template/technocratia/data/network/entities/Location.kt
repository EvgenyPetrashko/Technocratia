package com.template.technocratia.data.network.entities

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
) {
    override fun toString(): String = "st.${street.name}, $city, $country"
}