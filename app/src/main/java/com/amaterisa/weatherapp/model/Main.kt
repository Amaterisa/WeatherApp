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

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°C"
    }

    fun getMinTempString(): String {
        return "Min Temp " + minTemp.toString().substringBefore(".") + "°C"
    }

    fun getMaxTempString(): String {
        return "Max Temp " + maxTemp.toString().substringBefore(".") + "°C"
    }
}