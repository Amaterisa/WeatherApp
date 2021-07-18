package com.amaterisa.weatherapp.model

data class City(
    val name: String,
    val country: String,
    val coord: Coord
){
    fun getLocation(): String {
        return "$name, $country"
    }
}
