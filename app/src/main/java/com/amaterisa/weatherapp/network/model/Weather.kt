package com.amaterisa.weatherapp.network.model

import com.squareup.moshi.Json

data class Weather(
    val id: Int,
    @Json(name = "main") val weather: String,
    val description: String,
     val icon: String
) {
}