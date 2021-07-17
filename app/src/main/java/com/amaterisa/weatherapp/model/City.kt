package com.amaterisa.weatherapp.model

data class City(
    val name: String,
    val country: String
){
    fun getLocation(): String {
        return "$name, $country"
    }
}
