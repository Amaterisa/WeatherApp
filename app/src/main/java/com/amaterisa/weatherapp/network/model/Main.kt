package com.amaterisa.weatherapp.network.model

import com.squareup.moshi.Json

data class Main(
    val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "temp_min") val minTemp: Double,
    @Json(name = "temp_max") val maxTemp: Double,
    val pressure: Double,
    val humidity: Double
) {
}